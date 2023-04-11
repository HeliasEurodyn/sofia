package com.crm.sofia.services.html_template;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.html_template.HtmlTemplateDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.html_template.HtmlTemplateMapper;
import com.crm.sofia.model.html_template.HtmlTemplate;
import com.crm.sofia.repository.html_template.HtmlTemplateRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.common.emailSender.EmailService;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HtmlTemplateService {
    @Autowired
    HtmlTemplateRepository htmlTemplateRepository;

    @Autowired
    HtmlTemplateMapper htmlTemplateMapper;

    @Autowired
    JWTService jwtService;
    Map<String, Object> tokens = new HashMap<>();
    @Autowired
    private ComponentRetrieverService componentRetrieverService;
    @Autowired
    private ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;

    @Autowired
    private EmailService emailService;
    public HtmlTemplateService() {
    }

    public HtmlTemplateDTO getObject(String id) {
        HtmlTemplate entity = htmlTemplateRepository.findById(id).orElseThrow(() -> new DoesNotExistException("HtmlTemplate Does Not Exist"));

        HtmlTemplateDTO dto = htmlTemplateMapper.map(entity);

        List<ComponentPersistEntityDTO> sorted = this.shortCPEList(dto.getComponent().getComponentPersistEntityList());
        dto.getComponent().setComponentPersistEntityList(sorted);

        return dto;
    }

    public List<ComponentPersistEntityDTO> shortCPEList(List<ComponentPersistEntityDTO> componentPersistEntityList) {
        if (componentPersistEntityList == null) {
            return null;
        }

        componentPersistEntityList
                .stream()
                .filter(cpe -> cpe.getShortOrder() == null)
                .forEach(cpe -> cpe.setShortOrder(0L));

        List<ComponentPersistEntityDTO> sorted =
                componentPersistEntityList
                        .stream()
                        .sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());

        sorted.stream().forEach(cpe -> {

            cpe.getComponentPersistEntityFieldList()
                    .stream()
                    .filter(cpef -> cpef.getPersistEntityField().getShortOrder() == null)
                    .forEach(cpef -> cpef.getPersistEntityField().setShortOrder(0L));

            cpe.getComponentPersistEntityFieldList()
                    .stream()
                    .forEach(cpef -> cpef.setShortOrder(cpef.getPersistEntityField().getShortOrder()));

            List<ComponentPersistEntityFieldDTO> sortedCpefList =
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .sorted(Comparator.comparingLong(ComponentPersistEntityFieldDTO::getShortOrder)).collect(Collectors.toList());
            cpe.setComponentPersistEntityFieldList(sortedCpefList);
        });

        sorted.stream().forEach(cpe -> {
            List<ComponentPersistEntityDTO> sortedCPE = this.shortCPEList(cpe.getComponentPersistEntityList());
            cpe.setComponentPersistEntityList(sortedCPE);
        });

        return sorted;
    }

    private ComponentDTO retrieveComponentData(ComponentDTO componentDTO, String htmlTemplateId, String selectionId) {

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList = this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(componentDTO.getComponentPersistEntityList(), "html_template", htmlTemplateId);
        componentDTO.setComponentPersistEntityList(componentPersistEntityList);

        /* Retrieve Data */
        componentDTO = componentRetrieverService.retrieveComponentWithData(componentDTO, selectionId);

        return componentDTO;
    }

    public String printPreview(String token) {
        Map<String, Object> values = (Map<String, Object>) this.tokens.get(token);
        this.tokens.remove(token);
        return this.createHtmlTemplate((String) values.get("id"), (String) values.get("selectionId"));
    }
    public ResponseEntity<Map<String,String>> sendEmail(String htmlTemplateId, String selectionId, String subject, List<String> recipients) throws MessagingException {
        String content = this.createHtmlTemplate(htmlTemplateId,selectionId);
        emailService.sendHtmlEmail(subject,recipients,content);

        Map<String,String> response = new HashMap<>();
        response.put("message","The email has been sent successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public String createHtmlTemplate(String htmlTemplateId, String selectionId) {
        /* Retrieve Html Template & Component */
        HtmlTemplateDTO htmlTemplateDTO = this.getObject(htmlTemplateId);
        ComponentDTO componentDTO = htmlTemplateDTO.getComponent();

        /* Retrieve Component Data */
        componentDTO = this.retrieveComponentData(componentDTO, htmlTemplateId, selectionId);

        /* Replace Parameters to Html Template */
        String html = htmlTemplateDTO.getHtml();
        Map<String, String> valuesOfFields = new HashMap<>();

        /* Replace Multiline List Parameters */
        for (ComponentPersistEntityDTO cpe : componentDTO.flatComponentPersistEntityTree().collect(Collectors.toList())) {

            if(!(cpe.getMultiDataLine() == null? false : cpe.getMultiDataLine())){
                continue;
            }

            Pattern pattern = Pattern.compile(   "(##multiline\\."+cpe.getCode()+"\\.start##)((\\r\\n|\\r|\\n|.)*?)(##multiline\\."+cpe.getCode()+"\\.end##)"  );
            Matcher matcher = pattern.matcher(html);

            while (matcher.find())
            {
                    List<String> subHtmls = new ArrayList<>();
                    String matchedHtml = matcher.group();

                    System.out.println("------matchedHtml------");
                    System.out.println(matchedHtml);

                    for (ComponentPersistEntityDataLineDTO dl : cpe.getComponentPersistEntityDataLines()) {
                        String subHtml = matcher.group();
                        Map<String, String> valuesOfDLFields = new HashMap<>();
                        subHtml = subHtml.replace("##multiline."+cpe.getCode()+".start##","");
                        subHtml = subHtml.replace("##multiline."+cpe.getCode()+".end##","");

                        for (ComponentPersistEntityFieldDTO cpef : dl.getComponentPersistEntityFieldList()) {
                            if (subHtml.contains("##" + cpe.getCode() + "." + cpef.getPersistEntityField().getName() + "##")) {
                                String value = (cpef.getValue() == null ? "" : cpef.getValue().toString());
                                valuesOfDLFields.put("##" + cpe.getCode() + "." + cpef.getPersistEntityField().getName() + "##", value);
                            }
                        }

                        for (ComponentPersistEntityDTO childCpe :  dl.flatSingleLineCPETree().collect(Collectors.toList())) {
                            for (ComponentPersistEntityFieldDTO childCpef :  childCpe.getComponentPersistEntityFieldList()) {
                                if (subHtml.contains("##" + childCpe.getCode() + "." + childCpef.getPersistEntityField().getName() + "##")) {
                                    String value = (childCpef.getValue() == null ? "" : childCpef.getValue().toString());
                                    valuesOfDLFields.put("##" + childCpe.getCode() + "." + childCpef.getPersistEntityField().getName() + "##", value);
                                }
                            }
                        }

                        for (Map.Entry<String, String> entry : valuesOfDLFields.entrySet())
                            subHtml = subHtml.replace(entry.getKey(), entry.getValue());

                        subHtmls.add(subHtml);
                    }
                    html = html.replace(matchedHtml, String.join("", subHtmls));

            }
        }

        /* Replace Single List Parameters */
        for (ComponentPersistEntityDTO cpe : componentDTO.flatComponentPersistEntityTree().collect(Collectors.toList())) {
            for (ComponentPersistEntityFieldDTO cpef : cpe.getComponentPersistEntityFieldList()) {
                if (html.contains("##" + cpe.getCode() + "." + cpef.getPersistEntityField().getName() + "##")) {
                    String value = (cpef.getValue() == null ? "" : cpef.getValue().toString());
                    valuesOfFields.put("##" + cpe.getCode() + "." + cpef.getPersistEntityField().getName() + "##", value);
                }
            }
        }

        /* Replace Retrieved Parameters */
        for (Map.Entry<String, String> entry : valuesOfFields.entrySet())
            html = html.replace(entry.getKey(), entry.getValue());

        /* Add Javascript window.print() fucntion at the end of the header */
        html = html.replace("<body>", "<body><script>window.print();</script>");

        return html;
    }

    public String getToken(String id, String selectionId) {
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("id", id);
            put("selectionId", selectionId);
            put("createdOn", Instant.now());
        }};

        this.tokens.put(uuid, map);
        return uuid;
    }
}
