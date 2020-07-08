package com.crm.sofia.repository.table;

import com.crm.sofia.model.table.Table;
import com.crm.sofia.repository.common.BaseRepository;
import com.crm.sofia.repository.persistEntity.PersistEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends PersistEntityRepository<Table> {

    List<Table> findAll();


    @Query(" SELECT DISTINCT c " +
            " FROM Table c " +
            " JOIN FETCH c.tableFieldList  " +
            " WHERE c.id =:id ")
    Optional<Table> findCustomObjectById(@Param("id") Long id);

}
