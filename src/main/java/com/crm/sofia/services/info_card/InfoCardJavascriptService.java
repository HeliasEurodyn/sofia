package com.crm.sofia.services.info_card;

import com.crm.sofia.model.sofia.info_card.InfoCard;
import com.crm.sofia.utils.JSMin;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class InfoCardJavascriptService {

    public String generateDynamicScript(InfoCard infoCard) {

        List<String> nativeHandlerLines = new ArrayList<>();

        String classInitString = this.generateClassInit(infoCard);
        nativeHandlerLines.add(classInitString);

        String pointerVars = generatePointerVars();
        nativeHandlerLines.add(pointerVars);

        String userScriptsString = this.generateUserScripts(infoCard);
        nativeHandlerLines.add(userScriptsString);

        String nativeFormEventsHandlerString = this.generateNativeListEventsHandler();
        nativeHandlerLines.add(nativeFormEventsHandlerString);

        String classEndString = this.generateClassEnd();
        nativeHandlerLines.add(classEndString);

        return String.join("\n", nativeHandlerLines);
    }

    private String generateNativeListEventsHandler() {
        List<String> nativeFormEventsHandler = new ArrayList<>();
        nativeFormEventsHandler.add("");
        nativeFormEventsHandler.add("nativeListEventsHandler(type, metadata) {");

        nativeFormEventsHandler.
                add("if((type == 'onInfoCardOpen') && " +
                        "(typeof this.onInfoCardOpen == \"function\")) " +
                        "this.onInfoCardOpen(metadata);");

        nativeFormEventsHandler.
                add("if((type == 'onInfoCardDataLoaded') && " +
                        "(typeof this.onInfoCardDataLoaded == \"function\")) " +
                        "this.onInfoCardDataLoaded(metadata);");

        nativeFormEventsHandler.add("}");
        return String.join("\n", nativeFormEventsHandler);
    }

    private String generateUserScripts(InfoCard infoCard) {
        List<String> decodedScripts = new ArrayList<>();
        infoCard.getScripts().forEach(listScript -> {
            byte[] decodedBytes = Base64.getDecoder().decode(listScript.getScript());
            String decodedScript = new String(decodedBytes);
            decodedScripts.add(decodedScript);
        });

        return String.join("\n", decodedScripts);
    }

    private String generateClassInit(InfoCard infoCard) {
        List<String> classLines = new ArrayList<>();

        List<String> classDefLines = new ArrayList<>();
        classDefLines.add("class InfoCardDynamicScript");
        classDefLines.add(infoCard.getId().replace("-","_"));
        classDefLines.add(" {");
        String classDef = String.join("", classDefLines);

        String constructor = "constructor() {}";

        classLines.add(classDef);
        classLines.add(constructor);

        return String.join("\n", classLines);
    }

    private String generateClassEnd() {
        return "}";
    }

    private String generatePointerVars() {
        List<String> pointerVarLines = new ArrayList<>();
        pointerVarLines.add("");
        pointerVarLines.add("infoCardRef = null;");
        pointerVarLines.add("");
        pointerVarLines.add("navigateRef = null;");
        pointerVarLines.add("getFromBackendRef = null;");
        pointerVarLines.add("");
        pointerVarLines.add("defineInfoCardNavigator(myCallback){this.navigateRef = myCallback;}");
        pointerVarLines.add("navigate(command){return this.navigateRef(command);}");

        pointerVarLines.add("defineGetFromBackend(myCallback){this.getFromBackendRef = myCallback;}");
        pointerVarLines.add("getFromBackend(url, callback){return this.getFromBackendRef(url, callback);}");

        pointerVarLines.add("defineGetFromUrl(myCallback){this.getFromUrlRef = myCallback;}");
        pointerVarLines.add("getFromUrl(url, callback){return this.getFromUrlRef(url, callback);}");

        pointerVarLines.add("");
        return String.join("\n", pointerVarLines);
    }

    public String minify(String script) throws Exception {
        Reader reader = new StringReader(script);
        Writer writer = new StringWriter();
        JSMin jsMin = new JSMin(reader, writer);
        jsMin.jsmin();
        String scriptMin = writer.toString();
        return scriptMin;
    }
}
