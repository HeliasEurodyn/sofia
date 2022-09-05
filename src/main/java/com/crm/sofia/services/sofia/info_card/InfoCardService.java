package com.crm.sofia.services.sofia.info_card;

import com.crm.sofia.dto.sofia.info_card.InfoCardDTO;
import com.crm.sofia.dto.sofia.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.mapper.sofia.info_card.InfoCardMapper;
import com.crm.sofia.model.sofia.info_card.InfoCard;
import com.crm.sofia.native_repository.sofia.info_card.InfoCardNativeRepository;
import com.crm.sofia.repository.sofia.info_card.InfoCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InfoCardService {

    private final InfoCardRepository infoCardRepository;
    private final InfoCardMapper infoCardMapper;
    private final InfoCardNativeRepository infoCardNativeRepository;

    public InfoCardService(InfoCardRepository infoCardRepository,
                           InfoCardMapper infoCardMapper,
                           InfoCardNativeRepository infoCardNativeRepository) {
        this.infoCardRepository = infoCardRepository;
        this.infoCardMapper = infoCardMapper;
        this.infoCardNativeRepository = infoCardNativeRepository;
    }

    public InfoCardDTO getObject(String id,
                                 Map<String, String> parameters) {
        Optional<InfoCard> optionalInfoCard = this.infoCardRepository.findById(id);
        if (!optionalInfoCard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Info card does not exist");
        }
        InfoCardDTO infoCardDTO = this.infoCardMapper.map(optionalInfoCard.get());
        InfoCardTextResponceDTO infoCardTextResponceDTO = this.infoCardNativeRepository.getData(infoCardDTO.getQuery(), parameters);
        infoCardDTO.setCardText(infoCardTextResponceDTO.getCardText());
        infoCardDTO.setTitle(infoCardTextResponceDTO.getTitle());
        infoCardDTO.setIcon(infoCardTextResponceDTO.getIcon());
        infoCardDTO.setIconColor(infoCardTextResponceDTO.getIconColor());
        infoCardDTO.setQuery("");
        return infoCardDTO;
    }

    public String getJavaScriptFactory() {
        List<String> ids = this.infoCardRepository.getListIds();
        List<String> scriptLines = new ArrayList<>();
        scriptLines.add("function newInfoCardDynamicScript(id) {");
        ids.forEach(id -> {
            String ifClause =
                    String.join("",
                            "if (id == '" , id,
                            "' ) return new InfoCardDynamicScript",id.replace("-","_") , "();" );
            scriptLines.add(ifClause);
        });
        scriptLines.add("}");

        return String.join("\n", scriptLines);
    }
    
    public String getJavaScript(String id) {
        String script = this.infoCardRepository.getListScript(id);
        return script;
    }

    public String getMinJavaScript(String id) {
        String script = this.infoCardRepository.getListMinScript(id);
        return script;
    }
}
