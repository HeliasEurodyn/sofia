package com.crm.sofia.services.download;

import com.crm.sofia.dto.sofia.download.DownloadDTO;
import com.crm.sofia.mapper.download.DownloadMapper;
import com.crm.sofia.model.sofia.download.Download;
import com.crm.sofia.repository.download.DownloadRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DownloadService {
    @Autowired
    private DownloadMapper downloadMapper;
    @Autowired
    private DownloadRepository downloadRepository;
    @Autowired
    private JWTService jwtService;

    public List<DownloadDTO> getObject() {
        List<Download> entites = downloadRepository.findAll();
        return downloadMapper.map(entites);
    }

    public DownloadDTO getObject(String id) {
        Optional<Download> optionalEntity = downloadRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Download entity = optionalEntity.get();
        DownloadDTO dto = downloadMapper.map(entity);
        return dto;
    }

    //    @Transactional
    public DownloadDTO postObject(DownloadDTO downloadDto) {

        Download download = downloadMapper.map(downloadDto);
        if (downloadDto.getId() == null) {
            download.setCreatedOn(Instant.now());
            download.setCreatedBy(jwtService.getUserId());
        }
        download.setModifiedOn(Instant.now());
        download.setModifiedBy(jwtService.getUserId());
        Download savedData = downloadRepository.save(download);

        return downloadMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<Download> optionalEntity = downloadRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        downloadRepository.deleteById(optionalEntity.get().getId());
    }

}
