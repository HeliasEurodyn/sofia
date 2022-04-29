package com.crm.sofia.services.sofia.appview;

import com.crm.sofia.dto.sofia.appview.AppViewDTO;
import com.crm.sofia.dto.sofia.appview.AppViewFieldDTO;
import com.crm.sofia.mapper.sofia.appview.AppViewMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import com.crm.sofia.repository.sofia.persistEntity.PersistEntityRepository;
import com.crm.sofia.services.sofia.component.ComponentDesignerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AppViewService {

    @Value("${sofia.db.name}")
    private String sofiaDatabase;

    private PersistEntityRepository appViewRepository;
    private AppViewMapper appViewMapper;
    private EntityManager entityManager;
    private final ComponentDesignerService componentDesignerService;

    public AppViewService(PersistEntityRepository appViewRepository,
                          AppViewMapper appViewMapper,
                          EntityManager entityManager,
                          ComponentDesignerService componentDesignerService) {
        this.appViewRepository = appViewRepository;
        this.appViewMapper = appViewMapper;
        this.entityManager = entityManager;
        this.componentDesignerService = componentDesignerService;
    }

    public AppViewDTO postObject(AppViewDTO appViewDTO) {

        /**
         * Remove deleted Fields From Components
         */
        this.componentDesignerService.removeComponentTableFieldsByTable(
                appViewDTO.getId(),
                appViewDTO.getAppViewFieldList()
                        .stream()
                        .map( x -> x.getId())
                        .collect(Collectors.toList())
        );

        /**
         * Map And Save DTO
         */
        PersistEntity appView = this.appViewMapper.map(appViewDTO);
        PersistEntity createdAppView = this.appViewRepository.save(appView);

        /**
         * Add new Fields From Components
         */
        this.componentDesignerService.insertComponentTableFieldsByTable(createdAppView);

        return this.appViewMapper.map(createdAppView);
    }


    public List<AppViewDTO> getObject() {
        List<PersistEntity> views = this.appViewRepository.findByEntitytype("AppView");
        return this.appViewMapper.map(views);
    }

    public AppViewDTO getObject(String id) {
        Optional<PersistEntity> optionalView = this.appViewRepository.findById(id);
        if (!optionalView.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "View does not exist");
        }
        return this.appViewMapper.map(optionalView.get());
    }

    public void deleteObject(String id) {
        Optional<PersistEntity> optionalView = this.appViewRepository.findById(id);
        if (!optionalView.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "View does not exist");
        }
        this.appViewRepository.deleteById(optionalView.get().getId());
    }

    @Transactional
    public List<String> getViews() {
        Query query = entityManager.createNativeQuery("SELECT view_name FROM information_schema.views WHERE view_schema='"+sofiaDatabase+"';");
        List<String> viewNames = query.getResultList();
        return viewNames;
    }


    @Transactional
    public List<String> getViewFields(String viewName) {
        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + viewName + " FROM "+sofiaDatabase+";");
        List<Object[]> fields = query.getResultList();
        List<String> fieldNames = fields.stream().map(f -> f[0].toString()).collect(Collectors.toList());

        return fieldNames;
    }

    @Transactional
    public void deteleDatabaseView(String viewName) {
        Query query = entityManager.createNativeQuery("DROP TABLE " + viewName.replace(" ", "") + ";");
        query.executeUpdate();
    }

    @Transactional
    public void updateDatabaseView(AppViewDTO customComponentDTO) {
    }

    @Transactional
    public void createDatabaseView(AppViewDTO customComponentDTO) {
    }

    public Boolean viewOnDatabase(String viewName) {
        List<String> views = this.getViews();
        if (views.contains(viewName)) return true;
        else return false;
    }

    @Transactional
    public List<AppViewFieldDTO> generateViewFields(String sql) {

        List<AppViewFieldDTO> dtos = new ArrayList<>();
        String uuid = UUID.randomUUID().toString().replace("-", "_");
        this.createView(uuid, sql);

        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + uuid + " FROM "+sofiaDatabase+";");
        List<Object[]> fields = query.getResultList();

        for (Object[] field : fields) {
            AppViewFieldDTO dto = new AppViewFieldDTO();
            dto.setName(field[0].toString());
            dto.setDescription("");
            dto.setType(field[1].toString());
            dto.setEntitytype("AppViewField");

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


    public void dropView(String name) {
        String sql = "DROP VIEW IF EXISTS "+sofiaDatabase+"." + name;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    public void alterView(String name, String queryStr) {
        String sql = "ALTER VIEW IF EXISTS "+sofiaDatabase+"." + name + " AS " + queryStr;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }


    public void createView(String name, String queryStr) {
        String sql = "CREATE VIEW IF NOT EXISTS "+sofiaDatabase+"." + name + " AS " + queryStr;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }


}
