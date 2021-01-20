package com.crm.sofia.repository.form;

import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface FormRepository extends BaseRepository<FormEntity> {
    List<FormEntity> findAll();
}
