package com.crm.sofia.repository.sofia.data_transfer;

import com.crm.sofia.model.sofia.data_transfer.DataTransfer;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataTransferRepository extends BaseRepository<DataTransfer> {
    List<DataTransfer> findAll();

    @Query(" SELECT DISTINCT d.currentVersion FROM DataTransfer d " +
            " WHERE d.id =:id ")
    public Integer getCurrentVersion(@Param("id") Long id);

}
