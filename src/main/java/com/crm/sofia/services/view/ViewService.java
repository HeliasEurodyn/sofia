package com.crm.sofia.services.view;

import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.dto.view.ViewFieldDTO;
import com.crm.sofia.mapper.view.ViewMapper;
import com.crm.sofia.model.view.View;
import com.crm.sofia.repository.view.ViewRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class ViewService {

    private final ViewRepository viewRepository;
    private final ViewMapper viewMapper;
    private final EntityManager entityManager;

    public ViewService(ViewRepository viewRepository,
                       ViewMapper viewMapper,
                       EntityManager entityManager) {
        this.viewRepository = viewRepository;
        this.viewMapper = viewMapper;
        this.entityManager = entityManager;
    }


    public ViewDTO postObject(ViewDTO viewDTO) {
        View view = this.viewMapper.map(viewDTO);

        View createdView = this.viewRepository.save(view);
        return this.viewMapper.map(createdView);
    }

    @Transactional
    public ViewDTO putObject(ViewDTO viewDTO) {
        return null;
    }


    public List<ViewDTO> getObject() {
        List<View> views = this.viewRepository.findAll();
        return this.viewMapper.map(views);
    }

    public ViewDTO getObject(Long id) {
        Optional<View> optionalView = this.viewRepository.findById(id);
        if (!optionalView.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "View does not exist");
        }
        return this.viewMapper.map(optionalView.get());
    }

    public void deleteObject(Long id) {
        Optional<View> optionalView = this.viewRepository.findById(id);
        if (!optionalView.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "View does not exist");
        }
        this.viewRepository.deleteById(optionalView.get().getId());
    }

    @Transactional
    public List<String> getViews() {
        Query query = entityManager.createNativeQuery("SELECT view_name FROM information_schema.views WHERE view_schema='sofia';");
        List<String> viewNames = query.getResultList();
        return viewNames;
    }


    @Transactional
    public List<String> getViewFields(String viewName) {
        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + viewName + " FROM sofia;");
        List<Object[]> fields = query.getResultList();
        List<String> fieldNames = fields.stream().map(f -> f[0].toString()).collect(Collectors.toList());

        return fieldNames;
    }

    @Transactional
    public void deteleDatabaseView(String viewName) {
        Query query = entityManager.createNativeQuery("DROP TABLE "+viewName.replace(" ","")+";");
        query.executeUpdate();
    }

    public Boolean viewOnDatabase(String viewName) {
        List<String> views = this.getViews();
        if (views.contains(viewName)) return true;
        else return false;
    }

    @Transactional
    public List<ViewFieldDTO> generateViewFields(String sql) {

        List<ViewFieldDTO> dtos = new ArrayList<>();
       String uuid = UUID.randomUUID().toString().replace("-","_");
       this.createView(uuid, sql);

        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + uuid + " FROM sofia;");
        List<Object[]> fields = query.getResultList();

        for(Object[] field : fields){
            ViewFieldDTO dto = new ViewFieldDTO();
            dto.setName(field[0].toString());
            dto.setDescription("");
            dto.setType(field[1].toString());

            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(field[1].toString());
            while(m.find()) {
             dto.setSize(Integer.valueOf(m.group(1)));
            }

            dtos.add(dto);
        }
        this.dropView(uuid);
        return dtos;
    }

    @Transactional
    @Modifying
    public void dropView(String name) {
        String sql = "DROP VIEW IF EXISTS sofia."+name ;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public void alterView(String name, String queryStr) {
        String sql = "ALTER VIEW IF EXISTS sofia."+name + " AS " + queryStr;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public void createView(String name, String queryStr) {
        String sql = "CREATE VIEW IF NOT EXISTS sofia."+name + " AS " + queryStr;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public ViewDTO saveDTOAndCreate(ViewDTO dto) {
        ViewDTO customComponentDTO = this.postObject(dto);
        this.dropView(customComponentDTO.getName());
        this.createView(
                customComponentDTO.getName(),
                customComponentDTO.getQuery());

        return customComponentDTO;
    }
}
