package com.crm.sofia.utils.xhtml_to_pdf;

import com.lowagie.text.DocumentException;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;

public class XhtmlToPdf {
    public static void xhtmlToStream(Document xhtml, OutputStream outputStream) throws DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        SharedContext sharedContext = renderer.getSharedContext();
        sharedContext.setPrint(true);
        sharedContext.setInteractive(false);
        renderer.setDocumentFromString(xhtml.html());
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
