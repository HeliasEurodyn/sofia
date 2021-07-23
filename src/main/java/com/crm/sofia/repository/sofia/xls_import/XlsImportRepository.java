package com.crm.sofia.repository.sofia.xls_import;

import com.crm.sofia.model.sofia.xls_import.XlsImport;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XlsImportRepository extends BaseRepository<XlsImport> {
    public List<XlsImport> findAll();
}
