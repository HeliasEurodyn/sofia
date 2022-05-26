package com.crm.sofia.repository.sofia.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.sofia.user.User;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    List<User> findAllByStatusIsNotLikeOrderByCreatedOn(AppConstants.Types.UserStatus status);

    List<User> findUsersByIdIn(List<String> ids);

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
   String findPasswordById(@Param("id") String id);

    User findByEmail(String email);

    User findByProviderAndProviderUserId(String provider, String providerId);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET" +
            " header_menu_id = 23," +
            " sidebar_menu_id = 25," +
            " login_nav_command = 'STATICPAGE[NAME:dashboard,LOCATE:(ID=1)]'," +
            " search_nav_command = 'POPUPPAGE[NAME:search,LOCATE:(ID=1),SEARCH-DEFAULT:#SEARCH-VALUE#,FOCUS:search-field-box]' " +
            " WHERE id = :id ", nativeQuery = true)
    void initiateUserInfo(@Param("id") String id);

    @Modifying
    @Query(value = "UPDATE User u SET" +
            " u.currentLanguage.id = :language_id " +
            " WHERE u.id = :id ")
    void updateCurrentLanguage(@Param("id") String id, @Param("language_id") Long languageId);

}
