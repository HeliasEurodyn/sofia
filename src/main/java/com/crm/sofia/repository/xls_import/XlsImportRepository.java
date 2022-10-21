package com.crm.sofia.repository.xls_import;

import com.crm.sofia.model.xls_import.XlsImport;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XlsImportRepository extends BaseRepository<XlsImport> {
    public List<XlsImport> findAll();
}
