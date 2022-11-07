package com.crm.sofia.repository.user;

import com.crm.sofia.model.user.Role;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Role findFirstByName(String name);

    List<Role> findAll();

    List<Role> findAllByOrderByModifiedOn();
}
