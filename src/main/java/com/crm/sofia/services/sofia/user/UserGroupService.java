package com.crm.sofia.services.sofia.user;

import com.crm.sofia.dto.sofia.user.UserGroupDTO;
import com.crm.sofia.mapper.sofia.user.UserGroupMapper;
import com.crm.sofia.model.sofia.user.UserGroup;
import com.crm.sofia.services.sofia.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.crm.sofia.repository.sofia.user.UserGroupRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private JWTService jwtService;

    public List<UserGroupDTO> getObject() {
        List<UserGroup> entites = userGroupRepository.findAll();
        return userGroupMapper.map(entites);
    }

    public UserGroupDTO getObject(Long id)  {
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
        if (userGroupDTO.getId() == null) {
            userGroup.setCreatedOn(Instant.now());
            userGroup.setCreatedBy(jwtService.getUserId());
        }
        userGroup.setModifiedOn(Instant.now());
        userGroup.setModifiedBy(jwtService.getUserId());
        UserGroup savedData = userGroupRepository.save(userGroup);
        return userGroupMapper.map(savedData);
    }

    public void deleteObject(Long id) {
        Optional<UserGroup> optionalEntity = userGroupRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        userGroupRepository.deleteById(optionalEntity.get().getId());
    }
}
