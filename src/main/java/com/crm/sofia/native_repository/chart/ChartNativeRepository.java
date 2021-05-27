package com.crm.sofia.native_repository.chart;

import com.crm.sofia.dto.chart.ChartFieldDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChartNativeRepository {

    @Value("${sofia.database}")
    private String sofiaDatabase;

    private final EntityManager entityManager;

    public ChartNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ChartFieldDTO> getData(List<ChartFieldDTO> chartFieldList, String sql) {
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> queryResults = query.getResultList();
        chartFieldList.forEach(chartField -> chartField.setDataset(new ArrayList<>()));
        for (Object[] queryResult : queryResults) {
            int i = 0;
            for (ChartFieldDTO chartField : chartFieldList) {
                chartField.getDataset().add(queryResult[i]);
                i++;
            }
        }
        return chartFieldList;
    }

    public List<ChartFieldDTO> generateFields(String sql) {

        List<ChartFieldDTO> dtos = new ArrayList<>();
        String uuid = UUID.randomUUID().toString().replace("-", "_");
        this.createView(uuid, sql);

        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + uuid + " FROM " + sofiaDatabase + ";");
        List<Object[]> fields = query.getResultList();

        for (Object[] field : fields) {
            ChartFieldDTO dto = new ChartFieldDTO();
            dto.setName(field[0].toString());
            dto.setType(field[1].toString());

            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(field[1].toString());
            while (m.find()) {
                dto.setSize(Integer.valueOf(m.group(1)));
            }

            int index = field[1].toString().indexOf("(");
            if (index > 0) {
                dto.setType(field[1].toString().substring(0, index));
            }

            dtos.add(dto);
        }

        this.dropView(uuid);

        return dtos;
    }

    private void createView(String name, String queryStr) {
        String sql = "CREATE VIEW IF NOT EXISTS " + sofiaDatabase + "." + name + " AS " + queryStr;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    private void dropView(String name) {
        String sql = "DROP VIEW IF EXISTS " + sofiaDatabase + "." + name;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
}
