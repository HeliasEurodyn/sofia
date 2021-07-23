package com.crm.sofia.services.sofia.info_card;

import com.crm.sofia.dto.sofia.info_card.InfoCardDTO;
import com.crm.sofia.dto.sofia.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.mapper.sofia.info_card.InfoCardMapper;
import com.crm.sofia.model.sofia.info_card.InfoCard;
import com.crm.sofia.native_repository.sofia.info_card.InfoCardNativeRepository;
import com.crm.sofia.repository.sofia.info_card.InfoCardRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InfoCardDesignerService {

    private final InfoCardRepository infoCardRepository;
    private final InfoCardMapper infoCardMapper;
    private final InfoCardNativeRepository infoCardNativeRepository;

    public InfoCardDesignerService(InfoCardRepository infoCardRepository,
                                   InfoCardMapper infoCardMapper,
                                   InfoCardNativeRepository infoCardNativeRepository) {
        this.infoCardRepository = infoCardRepository;
        this.infoCardMapper = infoCardMapper;
        this.infoCardNativeRepository = infoCardNativeRepository;
    }

    public List<InfoCardDTO> getObject() {
        List<InfoCard> infoCards = this.infoCardRepository.findAll();
        return this.infoCardMapper.map(infoCards);
    }

    public InfoCardDTO getObject(Long id) {
        Optional<InfoCard> optionalInfoCard = this.infoCardRepository.findById(id);
        if (!optionalInfoCard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Info card does not exist");
        }
        return this.infoCardMapper.map(optionalInfoCard.get());
    }

    @Transactional
    @Modifying
    public InfoCardDTO postObject(InfoCardDTO dto) {
        InfoCard infoCard = this.infoCardMapper.map(dto);
        InfoCard createdInfoCard = this.infoCardRepository.save(infoCard);
        return this.infoCardMapper.map(createdInfoCard);
    }

    public void deleteObject(Long id) {
        Optional<InfoCard> optionalInfoCard = this.infoCardRepository.findById(id);
        if (!optionalInfoCard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "InfoCard does not exist");
        }
        this.infoCardRepository.deleteById(optionalInfoCard.get().getId());
    }

    public InfoCardTextResponceDTO getData(String sql) {
        return this.infoCardNativeRepository.getData(sql);
    }
}
