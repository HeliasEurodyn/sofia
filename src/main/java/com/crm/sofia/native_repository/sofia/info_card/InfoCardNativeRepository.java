package com.crm.sofia.native_repository.sofia.info_card;

import com.crm.sofia.dto.sofia.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InfoCardNativeRepository {

    private final JWTService jwtService;
    private final EntityManager entityManager;


    public InfoCardNativeRepository(JWTService jwtService, EntityManager entityManager) {
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    public InfoCardTextResponceDTO getData(String sql, Map<String, String> parameters) {
        sql = sql.replace("##user_id##", "'"+ this.jwtService.getUserId() + "'");
        Query query = entityManager.createNativeQuery(sql);

        String finalSql = sql;
        parameters.entrySet()
                .stream()
                .filter(x -> finalSql.contains(":" + x.getKey()))
                .forEach(x -> {
                    query.setParameter(x.getKey(),x.getValue());
                });

        List<Object> queryResults = query.getResultList();

        if (queryResults.isEmpty()) {
            return null;
        }

        if ( queryResults.get(0) instanceof Object[]) {
            for (Object queryResultArray : queryResults) {
                Object[] queryResult = (Object[]) queryResultArray;
                InfoCardTextResponceDTO infoCardTextResponceDTO = new InfoCardTextResponceDTO();
                infoCardTextResponceDTO.setCardText(queryResult[0].toString());
                infoCardTextResponceDTO.setTitle(queryResult[1].toString());
                infoCardTextResponceDTO.setIcon(queryResult[2].toString());
                infoCardTextResponceDTO.setIconColor(queryResult[3].toString());
                return infoCardTextResponceDTO;
            }
        }
        else {
            for (Object queryResult : queryResults) {
                InfoCardTextResponceDTO infoCardTextResponceDTO = new InfoCardTextResponceDTO();
                infoCardTextResponceDTO.setCardText(queryResult.toString());
                return infoCardTextResponceDTO;
            }
        }

        return null;
    }
}
