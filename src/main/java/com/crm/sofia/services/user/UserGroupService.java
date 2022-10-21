package com.crm.sofia.services.user;

import com.crm.sofia.dto.user.UserGroupDTO;
import com.crm.sofia.mapper.user.UserGroupMapper;
import com.crm.sofia.model.sofia.user.User;
import com.crm.sofia.model.sofia.user.UserGroup;
import com.crm.sofia.repository.user.UserGroupRepository;
import com.crm.sofia.repository.user.UserRepository;
import com.crm.sofia.services.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserGroupService {

    private final UserGroupMapper userGroupMapper;
    private final UserGroupRepository userGroupRepository;
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public UserGroupService(UserGroupMapper userGroupMapper,
                            UserGroupRepository userGroupRepository,
                            JWTService jwtService,
                            UserService userService,
                            UserRepository userRepository) {
        this.userGroupMapper = userGroupMapper;
        this.userGroupRepository = userGroupRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public List<UserGroupDTO> getObject() {
        List<UserGroup> entites = userGroupRepository.findAll();
        return userGroupMapper.map(entites);
    }

    public UserGroupDTO getObject(String id)  {
        Optional<UserGroup> optionalEntity = userGroupRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        UserGroup entity = optionalEntity.get();
        UserGroupDTO dto = userGroupMapper.map(entity);
        return dto;
    }

    @Transactional
    public UserGroupDTO postObject(UserGroupDTO userGroupDTO) {
        UserGroup userGroup = userGroupMapper.map(userGroupDTO);

        List<String> userIds =
                userGroupDTO.getUsers()
                        .stream()
                        .filter(user -> user.getId() != null)
                        .map(user -> user.getId())
                        .distinct()
                        .collect(Collectors.toList());

        List<User> users = userRepository.findUsersByIdIn(userIds);
        userGroup.setUsers(users);

        if (userGroupDTO.getId() == null) {
            userGroup.setCreatedOn(Instant.now());
            userGroup.setCreatedBy(jwtService.getUserId());
        }
        userGroup.setModifiedOn(Instant.now());
        userGroup.setModifiedBy(jwtService.getUserId());
        UserGroup savedData = userGroupRepository.save(userGroup);

        return userGroupMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<UserGroup> optionalEntity = userGroupRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        userGroupRepository.deleteById(id);
    }

}
