package com.crm.sofia.repository.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.model.user.User;
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
            " header_menu_id = 'dcd336a1-f74f-4410-bbee-6f91d5dd2e83'," +
            " sidebar_menu_id = 'b220ea37-f6ce-474b-8d8d-28d78ba57f58'," +
            " login_nav_command = 'STATICPAGE[NAME:dashboard,LOCATE:(ID=b9b1394b-425c-4c33-a132-e28c23df995a)]'," +
            " search_nav_command = 'POPUPPAGE[NAME:search,LOCATE:(ID=1),SEARCH-DEFAULT:#SEARCH-VALUE#,FOCUS:search-field-box]' " +
            " WHERE id = :id ", nativeQuery = true)
    void initiateUserInfo(@Param("id") String id);

    @Modifying
    @Query(value = "UPDATE User u SET" +
            " u.currentLanguage.id = :language_id " +
            " WHERE u.id = :id ")
    void updateCurrentLanguage(@Param("id") String id, @Param("language_id") String languageId);

    @Query("SELECT new com.crm.sofia.dto.user.UserDTO(u.id,u.username) FROM User u " +
            "WHERE u.status <> :userStatus ORDER BY u.modifiedOn DESC")
    List<UserDTO> getAllUsers(AppConstants.Types.UserStatus userStatus);

}
