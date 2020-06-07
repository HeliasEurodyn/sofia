package com.crm.sofia.services.list;

import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.model.list.ListEntity;
import com.crm.sofia.repository.list.ListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final ListMapper listMapper;

    public ListService(ListRepository listRepository, ListMapper listMapper) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
    }

    @Transactional
    public ListDTO postObject(ListDTO listDTO) {
        ListEntity view = this.listMapper.map(listDTO);

        ListEntity createdListEntity = this.listRepository.save(view);
        return this.listMapper.map(createdListEntity);
    }

    @Transactional
    public ListDTO putObject(ListDTO listDTO) {
        return null;
    }


    public List<ListDTO> getObject() {
        List<ListEntity> views = this.listRepository.findAll();
        return this.listMapper.map(views);
    }

    public ListDTO getObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        return this.listMapper.map(optionalListEntity.get());
    }

    public void deleteObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        this.listRepository.deleteById(optionalListEntity.get().getId());
    }



}
