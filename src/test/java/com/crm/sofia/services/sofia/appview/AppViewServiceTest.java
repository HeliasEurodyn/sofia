package com.crm.sofia.services.sofia.appview;
import com.crm.sofia.dto.sofia.appview.AppViewDTO;
import com.crm.sofia.mapper.sofia.appview.AppViewMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import com.crm.sofia.repository.sofia.persistEntity.PersistEntityRepository;
import com.crm.sofia.services.appview.AppViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.*;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AppViewServiceTest {
    @Mock
    private PersistEntityRepository appViewRepository;

    @InjectMocks
    private AppViewService appViewService;

    private AppViewDTO appViewDTO;
    private List<AppViewDTO> appViewDTOlist;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private AppViewMapper appViewMapper ;

    private PersistEntity persistEntity;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;
    List<PersistEntity> persistEntityList;
    List<Object[]> data ;

    public AppViewServiceTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        appViewDTOlist = new ArrayList<>();
        persistEntityList = new ArrayList<>();
        appViewDTO = new AppViewDTO();
        appViewDTO.setDescription("Demo");
        appViewDTO.setName("Frodo Baggins");
        appViewDTO.setQuery("Test");
        appViewDTOlist.add(appViewDTO);
        persistEntity = new PersistEntity();
        persistEntity.setDescription("Demo");
        persistEntityList.add(persistEntity);

        data = new ArrayList<>();
        Object[] oj =new Object[2];
        oj[0] = 1;
        oj[1] = "2(2))";
        data.add(oj);
    }

//    @Test
//    public void postObjectTest() {
//        given(appViewRepository.save(ArgumentMatchers.any(PersistEntity.class))).willReturn(persistEntity);
//        given(appViewMapper.map(ArgumentMatchers.any(AppViewDTO.class))).willReturn(persistEntity);
//        AppViewDTO appView = appViewService.postObject(appViewDTO);
//    }

    @Test
    public void getObjectTest(){
        given(appViewRepository.findByEntitytypeOrderByModifiedOn(ArgumentMatchers.anyString())).willReturn(persistEntityList);
        List<AppViewDTO> list = appViewService.getObject();
        assertThat(list).isNotNull();
    }

    @Test
    public void getObjectByIdTest(){
        given(appViewRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(persistEntity));
        AppViewDTO dto = appViewService.getObject("6L");

    }

    @Test
    public void getObjectByIdWhenEmptyTest(){
        given(appViewRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            appViewService.getObject("6L");
        });

        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"View does not exist\"";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }
    @Test
    public void getDeleteByIdTest(){
        given(appViewRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(persistEntity));
        appViewService.deleteObject("6L");

    }
    @Test
    public void getDeleteByIdWhenEmptyTest(){
        given(appViewRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            appViewService.deleteObject("6L");
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"View does not exist\"";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,expectedMessage);
    }


    @Test
    public void getViewsTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        given(query.getResultList()).willReturn(anyList());
        appViewService.getViews();
    }
    @Test
    public void getViewFieldsTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        given(query.getResultList()).willReturn(anyList());
        appViewService.getViewFields("dummy");
    }

    @Test
    public void deteleDatabaseViewTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        given(query.executeUpdate()).willReturn(1);
        appViewService.deteleDatabaseView("dummy");
    }
    @Test
    public void viewOnDatabaseTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        given(query.getResultList()).willReturn(anyList());
        appViewService.viewOnDatabase("dummy");
    }

    @Test
    public void generateViewFieldsTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        given(query.getResultList()).willReturn(data);
        appViewService.generateViewFields("Select * from table");
    }

    @Test
    public void dropViewTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        appViewService.dropView("table");
    }

    @Test
    public void alterViewTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        appViewService.alterView("table","query");
    }
    @Test
    public void createViewTest(){
        Mockito.when(entityManager.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
        appViewService.createView("table","query");
    }
}
