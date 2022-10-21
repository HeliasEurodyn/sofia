package com.crm.sofia.services.sofia.user;

import com.crm.sofia.dto.sofia.user.UserGroupDTO;
import com.crm.sofia.mapper.sofia.user.UserGroupMapper;
import com.crm.sofia.model.sofia.user.UserGroup;
import com.crm.sofia.repository.user.UserGroupRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.user.UserGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserGroupServiceTest   {

    @Mock
    private UserGroupRepository userGroupRepository;
    @InjectMocks
    private UserGroupService userGroupService;

    @Mock
    private JWTService jwtService;
    private List<UserGroup> userGroupList;

    private UserGroup userGroup;
    private UserGroupDTO userGroupDto;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private UserGroupMapper userGroupMapper ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userGroupList = new ArrayList<>();
        userGroup = new UserGroup();
        userGroupDto = new UserGroupDTO();
        userGroup.setName("dummy");
    }

//    @Test
//    public void getObjectTest() {
//        given(userGroupRepository.findAll()).willReturn(userGroupList);
//        List<UserGroupDTO> list = userGroupService.getObject();
//        assertThat(list).isNotNull();
//    }
//
//    @Test
//    public void getObjectByIdTest(){
//        given(userGroupRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(userGroup));
//        UserGroupDTO dto = userGroupService.getObject(6L);
//    }
//
//    @Test
//    public void getObjectByIdWhenEmptyTest(){
//        given(userGroupRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.empty());
//        Exception exception = assertThrows(ResponseStatusException.class, () -> {
//            userGroupService.getObject(6L);
//        });
//        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
//        String actualMessage = exception.getMessage();
//        assertEquals(actualMessage,expectedMessage);
//    }
//    @Test
//    public void postObjectTest(){
//        given(userGroupRepository.save(ArgumentMatchers.any(UserGroup.class))).willReturn(userGroup);
//        given(userGroupMapper.map(ArgumentMatchers.any(UserGroupDTO.class))).willReturn(userGroup);
//        userGroupService.postObject(userGroupDto);
//    }
//
//    @Test
//    public void getDeleteByIdTest(){
//        given(userGroupRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(userGroup));
//        userGroupService.deleteObject(6L);
//    }

//    @Test
//    public void getDeleteByIdWhenEmptyTest(){
//        given(userGroupRepository.findById(6L)).willReturn(Optional.empty());
//        Exception exception = assertThrows(ResponseStatusException.class, () -> {
//            userGroupService.deleteObject(6L);
//        });
//        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
//        String actualMessage = exception.getMessage();
//        assertEquals(actualMessage,expectedMessage);
//    }

}
