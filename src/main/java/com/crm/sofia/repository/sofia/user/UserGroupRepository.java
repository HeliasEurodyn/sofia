package com.crm.sofia.repository.sofia.user;
import com.crm.sofia.model.sofia.user.UserGroup;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserGroupRepository extends BaseRepository<UserGroup> {
    List<UserGroup> findAll();
}
