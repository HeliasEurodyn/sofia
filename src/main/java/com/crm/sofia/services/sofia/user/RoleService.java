package com.crm.sofia.services.sofia.user;

import com.crm.sofia.dto.sofia.user.RoleDTO;
import com.crm.sofia.mapper.sofia.user.RoleMapper;
import com.crm.sofia.model.sofia.user.Role;
import com.crm.sofia.repository.sofia.user.RoleRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
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
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;

    public RoleService(RoleMapper roleMapper,
                       RoleRepository roleRepository,
                       JWTService jwtService) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    public List<RoleDTO> getObject() {
        List<Role> entites = roleRepository.findAllByOrderByModifiedOn();
        return roleMapper.map(entites);
    }

    public RoleDTO getObject(String id)  {
        Optional<Role> optionalEntity = roleRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Role entity = optionalEntity.get();
        RoleDTO dto = roleMapper.map(entity);
        return dto;
    }

    @Transactional
    public RoleDTO postObject(RoleDTO roleDTO) {
        Role role = roleMapper.map(roleDTO);

        if (roleDTO.getId() == null) {
            role.setCreatedOn(Instant.now());
            role.setCreatedBy(jwtService.getUserId());
        }
        role.setModifiedOn(Instant.now());
        role.setModifiedBy(jwtService.getUserId());
        Role savedData = roleRepository.save(role);

        return roleMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<Role> optionalEntity = roleRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        roleRepository.deleteById(id);
    }

}
