package com.crm.sofia.repository.info_card;

import com.crm.sofia.model.info_card.InfoCard;
import com.crm.sofia.repository.common.BaseRepository;
import java.util.List;

public interface InfoCardRepository extends BaseRepository<InfoCard> {

    public List<InfoCard> findAll();

}
