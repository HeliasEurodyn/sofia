package com.crm.sofia.repository.sofia.user;

import com.crm.sofia.model.sofia.user.Role;
import com.crm.sofia.repository.common.BaseRepository;

public interface RoleRepository  extends BaseRepository<Role> {

    Role findByName(String name);
}
