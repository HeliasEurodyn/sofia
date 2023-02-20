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
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public HtmlTemplateService() {
    }

    public HtmlTemplateDTO getObject(String id) {
        HtmlTemplate entity = htmlTemplateRepository.findById(id).orElseThrow(() -> new DoesNotExistException("HtmlTemplate Does Not Exist"));

        HtmlTemplateDTO dto = htmlTemplateMapper.map(entity);
        if (dto.getHtml() != null) {
            String encodedHtml = Base64.getEncoder().encodeToString(dto.getHtml().getBytes(StandardCharsets.UTF_8));
            dto.setHtml(encodedHtml);
        }

        return dto;
    }


//    public HtmlTemplateDTO generatePdf(String id) {
//        HtmlTemplate entity = htmlTemplateRepository.findById(id).orElseThrow(() -> new DoesNotExistException("HtmlTemplate Does Not Exist"));
//
//        HtmlTemplateDTO dto = htmlTemplateMapper.map(entity);
//
//        return dto;
//    }


//    public void download(String id, HttpServletResponse response) throws IOException, DocumentException {
//
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment;filename=" + "report.pdf");
//        response.setStatus(HttpServletResponse.SC_OK);
//        OutputStream outputStream = response.getOutputStream();
//
//        HtmlTemplateDTO htmlTemplateDTO = this.generatePdf(id);
//        Document xhtml = HtmlToXhtml.htmlToXhtml(htmlTemplateDTO.getHtml());
//        XhtmlToPdf.xhtmlToStream(xhtml, outputStream);
//
//        outputStream.flush();
//    }

//    public void print(String htmlTemplateId, String selectionId, HttpServletResponse response) throws IOException, DocumentException {
//        String html = this.createHtmlTemplate(htmlTemplateId, selectionId);
//
//        /* Set Response */
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment;filename=" + "report.pdf");
//        response.setStatus(HttpServletResponse.SC_OK);
//        OutputStream outputStream = response.getOutputStream();
//
//        Document xhtml = HtmlToXhtml.htmlToXhtml(html);
//        XhtmlToPdf.xhtmlToStream(xhtml, outputStream);
//
//        outputStream.flush();
//    }


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

    public String createHtmlTemplate(String htmlTemplateId, String selectionId) {
        /* Retrieve Html Template & Component */
        HtmlTemplate entity = htmlTemplateRepository.findById(htmlTemplateId).orElseThrow(() -> new DoesNotExistException("HtmlTemplate Does Not Exist"));
        HtmlTemplateDTO htmlTemplateDTO = htmlTemplateMapper.map(entity);
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

            Pattern pattern = Pattern.compile(   "(\\#\\#multiline\\."+cpe.getCode()+"\\.start\\#\\#)((\\r\\n|\\r|\\n|.)*?)(\\#\\#multiline\\."+cpe.getCode()+"\\.end\\#\\#)"  );
            Matcher matcher = pattern.matcher(html);

            if (matcher.find())
            {
                System.out.println(matcher.group(1));
                for (int i=0;i<matcher.groupCount();i++){
                    List<String> subHtmls = new ArrayList<>();
                    for (ComponentPersistEntityDataLineDTO dl : cpe.getComponentPersistEntityDataLines()) {
                        String subHtml = matcher.group(i);
                        Map<String, String> valuesOfDLFields = new HashMap<>();

                        for (ComponentPersistEntityFieldDTO cpef : dl.getComponentPersistEntityFieldList()) {
                            if (subHtml.contains("##" + cpe.getCode() + "." + cpef.getPersistEntityField().getName() + "##")) {
                                String value = (cpef.getValue() == null ? "" : cpef.getValue().toString());
                                valuesOfDLFields.put("##" + cpe.getCode() + "." + cpef.getPersistEntityField().getName() + "##", value);
                            }
                        }

                        for (Map.Entry<String, String> entry : valuesOfDLFields.entrySet())
                            subHtml = subHtml.replace(entry.getKey(), entry.getValue());

                        subHtmls.add(subHtml);
                    }

                }
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
