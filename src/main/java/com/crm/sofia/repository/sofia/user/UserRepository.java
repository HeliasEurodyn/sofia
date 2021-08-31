package com.crm.sofia.repository.sofia.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.sofia.user.User;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    List<User> findAllByStatusIsNotLike(AppConstants.Types.UserStatus status);

    @Query(" SELECT (count (u)>0) " +
            " FROM User u " +
            " WHERE u.username=:username " )
    boolean userExists(@Param("username") String username);


    @Query(" SELECT u " +
            " FROM User u " +
            " LEFT JOIN FETCH u.sidebarMenu sm " +
            " LEFT JOIN FETCH u.headerMenu hm " +
            " WHERE u.username = :username ")
    Optional<User> findByUsername(@Param("username") String username);

    @Query(" SELECT u.password " +
            " FROM User u " +
            " WHERE u.id = :id ")
   String findPasswordById(@Param("id") Long id);

}
