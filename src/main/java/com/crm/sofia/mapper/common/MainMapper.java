package com.crm.sofia.mapper.common;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class MainMapper<D extends BaseDTO, E extends MainEntity> {

//    @Autowired
//    BaseRepository<E> repository;

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    public abstract E map(D dto);

    public abstract D map(E entity);

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    public abstract void map(D dto, @MappingTarget E entity);

    public Page<D> map(Page<E> all) {
        return all.map(this::map);
    }

    public Iterable<E> mapDTOs(Collection<D> all) {
        return StreamSupport.stream(all.spliterator(), true).map(this::map)
                .collect(Collectors.toList());
    }

    public Collection<D> mapDTOs(Iterable<E> all) {
        return StreamSupport.stream(all.spliterator(), true).map(this::map)
                .collect(Collectors.toList());
    }

    public List<E> mapDTOs(List<D> all) {
        return StreamSupport.stream(all.spliterator(), true).map(this::map)
                .collect(Collectors.toList());
    }

    public List<D> map(List<E> all) {
        return all.stream().map(this::map).collect(Collectors.toList());
    }

    public String mapToEntityId(E entity) {
        return entity != null ? entity.getId() : null;
    }

}
