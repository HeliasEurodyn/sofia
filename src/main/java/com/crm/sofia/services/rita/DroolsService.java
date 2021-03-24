package com.crm.sofia.services.rita;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DroolsService {

    private KieServices kieServices = KieServices.Factory.get();

    private Map<String,KieSession>  kieSessions = new HashMap<>();

    public Boolean createKiaSession(String key, String rdlFile){

        if(this.kieSessions.containsKey(key)) return false;

        KieFileSystem kieFileSystem = getKieFileSystem(rdlFile);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        KieSession kieSession = kieContainer.newKieSession();
        this.kieSessions.put(key,kieSession);

        return true;
    }

    public List<String> getKeys(){
        return this.kieSessions.keySet().stream().collect(Collectors.toList());
    }

    public KieSession getKiaSession(String key){
      return this.kieSessions.containsKey(key) ? this.kieSessions.get(key) : null;
    }

    private KieFileSystem getKieFileSystem(String rdlFile) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(rdlFile));
        return kieFileSystem;
    }

//    private void getKieRepository() {
//        final KieRepository kieRepository = kieServices.getRepository();
//        kieRepository.addKieModule(new KieModule() {
//            public ReleaseId getReleaseId() {
//                return kieRepository.getDefaultReleaseId();
//            }
//        });
//    }

}
