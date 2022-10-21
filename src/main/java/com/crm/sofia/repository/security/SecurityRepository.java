package com.crm.sofia.repository.security;

import com.crm.sofia.model.security.Security;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityRepository extends BaseRepository<Security> {
    List<Security> findAll();
}
