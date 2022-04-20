package com.crm.sofia.services.sofia.data_transfer;

import com.crm.sofia.dto.sofia.data_transfer.DataTransferDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.dto.sofia.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.sofia.data_transfer.DataTransferMapper;
import com.crm.sofia.model.sofia.data_transfer.DataTransfer;
import com.crm.sofia.repository.sofia.data_transfer.DataTransferRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.form.FormDesignerService;
import com.crm.sofia.services.sofia.list.ListDesignerService;
import com.crm.sofia.utils.ByteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataTransferService {
    @Autowired
    private DataTransferMapper dataTransferMapper;

    @Autowired
    private DataTransferRepository dataTransferRepository;
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    private ListDesignerService listDesignerService;

    @Autowired
    private FormDesignerService formDesignerService;

    @Autowired
    private ByteUtils byteUtils;

    @Autowired
    private  EntityManager entityManager;

    public List<DataTransferDTO> getObject() {
        List<DataTransfer> entites = dataTransferRepository.findAll();
        return dataTransferMapper.map(entites);
    }

    public DataTransferDTO getObject(String id) {
        Optional<DataTransfer> optionalEntity = dataTransferRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        DataTransfer entity = optionalEntity.get();
        DataTransferDTO dto = dataTransferMapper.map(entity);
        return dto;
    }

    public DataTransferDTO postObject(DataTransferDTO downloadDto) {

        DataTransfer download = dataTransferMapper.map(downloadDto);
        if (downloadDto.getId() == null) {
            download.setCreatedOn(Instant.now());
            download.setCreatedBy(jwtService.getUserId());
        }
        download.setModifiedOn(Instant.now());
        download.setModifiedBy(jwtService.getUserId());
        DataTransfer savedData = dataTransferRepository.save(download);

        return dataTransferMapper.map(savedData);
    }

    public void deleteObject(String id) {
        DataTransfer entity = dataTransferRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist"));

        dataTransferRepository.deleteById(entity.getId());
    }

    public byte[] export(String id) throws IOException, ClassNotFoundException {

        DataTransferDTO dataTransferDTO = this.getObject(id);

        dataTransferDTO.setLists(new ArrayList<>());
        dataTransferDTO.getListIds().forEach(listId -> {
//            try {
                ListDTO listDTO = this.listDesignerService.getById(listId);
                dataTransferDTO.getLists().add(listDTO);
//            }catch (Exception ex){
//                String a = "ss";
//            }
        });

        dataTransferDTO.setForms(new ArrayList<>());
        dataTransferDTO.getFormIds().forEach(formId -> {
            FormDTO formDTO = this.formDesignerService.getObject(formId);
            dataTransferDTO.getForms().add(formDTO);
        });

        return byteUtils.objectToBase64Bytes(dataTransferDTO);
    }

    public void importFile(MultipartFile multipartFile) throws Exception {

        /* Check file extension */
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

        if (!extension.equals("sofia")) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong file type");
        }

        /* Bytes to DataTransferDTO */
        DataTransferDTO dataTransferDTO = (DataTransferDTO) byteUtils.base64BytesToObject(multipartFile.getBytes());

        Integer currentVersion = this.dataTransferRepository.getCurrentVersion(dataTransferDTO.getId());

        if((currentVersion==null?0:currentVersion) < dataTransferDTO.getCurrentVersion()){
            this.importDTO(dataTransferDTO);
        }

    }

    @Transactional
    public void importDTO(DataTransferDTO dataTransferDTO) throws Exception {

        Long version = this.getCurrentVersion("DataTransfer", dataTransferDTO.getId());
        dataTransferDTO.setVersion(version);
        this.postObject(dataTransferDTO);

        for (ListDTO listDTO : dataTransferDTO.getLists()) {
            version = this.getCurrentVersion("ListEntity", listDTO.getId());
            listDTO.setVersion(version);
            this.listDesignerService.postObject(listDTO);
        }

        for (FormDTO formDTO : dataTransferDTO.getForms()) {
            version = this.getCurrentVersion("FormEntity", formDTO.getId());
            formDTO.setVersion(version);
            this.formDesignerService.postObject(formDTO);
        }

    }

    private Long getCurrentVersion(String entity, String id){

        Query query = entityManager.
                createQuery("Select t.version from " + entity + " t WHERE t.id = :id");
        query.setParameter("id", id);
        List<Long> results = query.getResultList();

        if(results.size() == 0){
            return 0L;
        } else {
            return results.get(0);
        }

    }

}
