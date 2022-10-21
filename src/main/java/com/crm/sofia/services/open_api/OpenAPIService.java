package com.crm.sofia.services.open_api;

import com.crm.sofia.model.sofia.component.Component;
import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.repository.list.ListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenAPIService {



    @Value("${server.servlet.context-path}")
    private String basePath;

    @Value("${server.port}")
    private String port;

    private final FormRepository formRepository;
    private final ListRepository listRepository;

    public OpenAPIService(FormRepository formRepository,
                          ListRepository listRepository) {
        this.formRepository = formRepository;
        this.listRepository = listRepository;
    }

    public String generateYaml() {
        List<String> yamlLines = new ArrayList<>();

        yamlLines.add("swagger: \"2.0\"");
        yamlLines.add("info:");
        yamlLines.add("  version: \"1.0.0\"");
        yamlLines.add("  title: \"Rita\"");

        yamlLines.add("host: \"localhost\"");
        yamlLines.add("basePath: \""+basePath+"\"");

        yamlLines.add("schemes:");
        yamlLines.add("- \"https\"");
        yamlLines.add("- \"http\"");

        yamlLines.add("paths:");

        List<FormEntity> formEntities = formRepository.getIdsByExistingJsonUrls();
        formEntities.forEach(form -> {
            yamlLines.add("  /dataset/"+form.getJsonUrl()+":");
            yamlLines.add("    post:");
            yamlLines.add("      operationId: \"post-" + form.getId()+"\"");
            yamlLines.add("      consumes:");
            yamlLines.add("      - \"application/json\"");
            yamlLines.add("      produces:");
            yamlLines.add("      - \"application/json\"");
            yamlLines.add("      parameters:");
            yamlLines.add("      - in: \"body\"");
            yamlLines.add("        name: \"body\"");
            yamlLines.add("        required: true");
            yamlLines.add("        schema:");
            yamlLines.add("        $ref: \"#/definitions/"+form.getComponent().getComponentPersistEntityList().get(0).getCode()+"\"");
            yamlLines.add("      responses:");
            yamlLines.add("        \"200\":");
            yamlLines.add("          description: \"Success\"");
            yamlLines.add("        \"500\":");
            yamlLines.add("          description: \"Internal Server Error\"");

            yamlLines.add("  /dataset/"+form.getJsonUrl()+"/{id}:");
            yamlLines.add("    get:");
            yamlLines.add("      operationId: \"get-" + form.getId()+"\"");
            yamlLines.add("      produces:");
            yamlLines.add("      - \"application/json\"");
            yamlLines.add("      parameters:");
            yamlLines.add("      - name: \"id\"");
            yamlLines.add("        in: \"path\"");
            yamlLines.add("        required: true");
            yamlLines.add("        type: \"integer\"");
            yamlLines.add("        format: \"int64\"");
            yamlLines.add("      responses:");
            yamlLines.add("        \"200\":");
            yamlLines.add("          description: \"Success\"");
            yamlLines.add("          schema:");
            yamlLines.add("            $ref: \"#/definitions/"+form.getComponent().getComponentPersistEntityList().get(0).getCode()+"\"");
            yamlLines.add("        \"500\":");
            yamlLines.add("          description: \"Internal Server Error\"");
        });

        List<ListEntity> listEntities = listRepository.getIdsByExistingJsonUrls();
        listEntities.forEach(list -> {
            yamlLines.add("  /datalist/"+list.getJsonUrl()+"/{id}:");
            yamlLines.add("    get:");
            yamlLines.add("      operationId: \"get-list-" + list.getId()+"\"");
            yamlLines.add("      produces:");
            yamlLines.add("      - \"application/json\"");
            yamlLines.add("      parameters:");
            yamlLines.add("      - name: \"id\"");
            yamlLines.add("        in: \"path\"");
            yamlLines.add("        required: true");
            yamlLines.add("        type: \"integer\"");
            yamlLines.add("        format: \"int64\"");
            yamlLines.add("      responses:");
            yamlLines.add("        \"200\":");
            yamlLines.add("          description: \"Success\"");
            yamlLines.add("          schema:");
            yamlLines.add("            type: \"array\"");
            yamlLines.add("            items:");
            yamlLines.add("              $ref: \"#/definitions/"+list.getComponent().getComponentPersistEntityList().get(0).getCode()+"\"");
            yamlLines.add("        \"500\":");
            yamlLines.add("          description: \"Internal Server Error\"");
        });

        List<Component> formComponents =
                formEntities.stream()
                        .map(form -> form.getComponent())
                        .distinct()
                        .collect(Collectors.toList());

        List<Component> listComponents =
                listEntities.stream()
                        .map(list -> list.getComponent())
                        .distinct()
                        .collect(Collectors.toList());

        formComponents.addAll(listComponents);

        List<Component> components = formComponents.stream()
                .distinct()
                .collect(Collectors.toList());

        yamlLines.add("definitions:");

        components.forEach(component -> {
            component.getComponentPersistEntityList().forEach(cpe -> {
                yamlLines.add("  "+cpe.getCode()+":");
                yamlLines.add("    type: \"object\"");
                yamlLines.add("    properties:");
                cpe.getComponentPersistEntityFieldList().forEach(cpef -> {
                    yamlLines.add("      "+cpef.getPersistEntityField().getName() + ":");

                    String type = "";
                    if (cpef.getPersistEntityField().getType().equals("bigint")){
                        type = "integer";
                    }
                    yamlLines.add("        type: \""+ cpef.getPersistEntityField().getType() +"\"");
                });
            });
        });

        return String.join("\n", yamlLines);
    }
}
