package com.crm.sofia.controllers.html_template;

import com.crm.sofia.services.html_template.HtmlTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@Validated
@RequestMapping("/html-template")
public class HtmlTemplateController {
    @Autowired
    HtmlTemplateService htmlTemplateService;

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

    @PostMapping(value = "/send-email")
    ResponseEntity<Map<String, String>> sendEmail(@RequestParam("id") String id,
                                                  @RequestParam("selection-id") String selectionId,
                                                  @RequestParam("subject") String subject,
                                                  @RequestBody List<String> recipients) throws MessagingException {
        return htmlTemplateService.sendEmail(id, selectionId, subject, recipients);
    }

}
