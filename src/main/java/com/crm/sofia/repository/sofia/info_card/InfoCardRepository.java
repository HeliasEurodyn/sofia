package com.crm.sofia.repository.sofia.info_card;

import com.crm.sofia.model.sofia.info_card.InfoCard;
import com.crm.sofia.repository.common.BaseRepository;
import java.util.List;

public interface InfoCardRepository extends BaseRepository<InfoCard> {

    public List<InfoCard> findAll();

}
