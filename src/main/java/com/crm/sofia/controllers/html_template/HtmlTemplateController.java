package com.crm.sofia.controllers.html_template;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.user.ComponentUiDTO;
import com.crm.sofia.dto.html_template.HtmlTemplateDTO;
import com.crm.sofia.services.html_template.HtmlTemplateService;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;



@Slf4j
@RestController
@Validated
@RequestMapping("/html-template")
public class HtmlTemplateController {
    @Autowired
    HtmlTemplateService htmlTemplateService;

    @GetMapping(path = "/by-id")
    HtmlTemplateDTO getObject(@RequestParam("id") String id) {
        return htmlTemplateService.getObject(id);
    }

    @GetMapping(path = "/download2")
    public void print(@RequestParam("id") String id, HttpServletResponse response) throws IOException, DocumentException {
        htmlTemplateService.download(id, response);
    }

    @GetMapping(path = "/download")
    void download(@RequestParam("id") String htmlTemplateId,
                       @RequestParam("selection-id") String selectionId,
            HttpServletResponse response) throws DocumentException, IOException {
         this.htmlTemplateService.print(htmlTemplateId, selectionId, response);
    }

    @GetMapping(path = "/print-preview")
    Map printPreview(@RequestParam("id") String htmlTemplateId,
                     @RequestParam("selection-id") String selectionId) throws DocumentException, IOException {
        String html = this.htmlTemplateService.printPreview(htmlTemplateId, selectionId);
        return Collections.singletonMap("html",html);
    }


}
