package com.crm.sofia.repository.language;

import com.crm.sofia.model.sofia.language.Language;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends BaseRepository<Language> {

    public List<Language> findAll();

}
