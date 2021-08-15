package com.crm.sofia.native_repository.sofia.info_card;

import com.crm.sofia.dto.sofia.info_card.InfoCardTextResponceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class InfoCardNativeRepository {

    @Value("${sofia.db.name}")
    private String sofiaDatabase;

    private final EntityManager entityManager;


    public InfoCardNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public InfoCardTextResponceDTO getData(String sql) {

        Query query = entityManager.createNativeQuery(sql);
        List<Object> queryResults = query.getResultList();

        if (queryResults.isEmpty()) {
            return null;
        }

        if ( queryResults.get(0) instanceof Object[]) {
            for (Object queryResultArray : queryResults) {
                Object[] queryResult = (Object[]) queryResultArray;
                return new InfoCardTextResponceDTO(queryResult[0].toString());
            }
        }
        else {
            for (Object queryResult : queryResults) {
                return new InfoCardTextResponceDTO(queryResult.toString());
            }
        }

        return null;
    }
}
