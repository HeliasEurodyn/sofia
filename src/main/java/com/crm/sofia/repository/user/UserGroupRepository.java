package com.crm.sofia.repository.user;
import com.crm.sofia.model.user.UserGroup;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends BaseRepository<UserGroup> {
    List<UserGroup> findAll();
}
