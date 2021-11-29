package com.crm.sofia.repository.sofia.user;

import com.crm.sofia.model.sofia.user.Role;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role> {

    Role findByName(String name);

    List<Role> findAll();
}
