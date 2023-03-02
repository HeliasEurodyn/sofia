package com.crm.sofia.controllers.html_template;

import com.crm.sofia.dto.html_template.HtmlTemplateDTO;
import com.crm.sofia.services.html_template.HtmlTemplateService;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/instant-access-token")
    Map getToken(@RequestParam("id") String id,
                 @RequestParam("selection-id") String selectionId) {
        String token = this.htmlTemplateService.getToken(id, selectionId);
        return Collections.singletonMap("token", token);
    }

    @GetMapping(path = "/print-preview")
    Map printPreview(@RequestParam("token") String token) {
        String html = this.htmlTemplateService.printPreview(token);
        return Collections.singletonMap("html", html);
    }

    @GetMapping(value = "/preview-page.html", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    String preview(@RequestParam("token") String token) {
        return this.htmlTemplateService.printPreview(token);
    }

}
