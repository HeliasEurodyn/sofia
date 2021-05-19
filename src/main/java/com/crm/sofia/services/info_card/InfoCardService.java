package com.crm.sofia.services.info_card;

import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.dto.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.mapper.info_card.InfoCardMapper;
import com.crm.sofia.model.info_card.InfoCard;
import com.crm.sofia.repository.info_card.InfoCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class InfoCardService {

    private final InfoCardRepository infoCardRepository;
    private final InfoCardMapper infoCardMapper;
    private final InfoCardDynamicQueryService infoCardDynamicQueryService;

    public InfoCardService(InfoCardRepository infoCardRepository,
                           InfoCardMapper infoCardMapper,
                           InfoCardDynamicQueryService infoCardDynamicQueryService) {
        this.infoCardRepository = infoCardRepository;
        this.infoCardMapper = infoCardMapper;
        this.infoCardDynamicQueryService = infoCardDynamicQueryService;
    }

    public InfoCardDTO getObject(Long id) {
        Optional<InfoCard> optionalInfoCard = this.infoCardRepository.findById(id);
        if (!optionalInfoCard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Info card does not exist");
        }
        InfoCardDTO infoCardDTO = this.infoCardMapper.map(optionalInfoCard.get());
        InfoCardTextResponceDTO infoCardTextResponceDTO = this.infoCardDynamicQueryService.getData(infoCardDTO.getQuery());
        infoCardDTO.setCardText(infoCardTextResponceDTO.getCardText());
        return infoCardDTO;
    }

}
