package com.crm.sofia.services.sofia.timeline;

import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.mapper.sofia.timeline.TimelineMapper;
import com.crm.sofia.model.sofia.timeline.Timeline;
import com.crm.sofia.repository.sofia.timeline.TimelineRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TimelineServiceTest {

    @Mock
    private TimelineRepository timelineRepository;

    @InjectMocks
    private TimelineService timelineService;

    @Mock
    private JWTService jwtService;

    private List<Timeline> timelineList;

    private Timeline timeline;
    private TimelineDTO timelineDTO;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private TimelineMapper timelineMapper ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        timelineList = new ArrayList<>();
        timeline = new Timeline().setTitle("dummyTitle").setQuery("dummyQuery");
        timelineList.add(timeline);
        timelineDTO = new TimelineDTO().setTitle("dummyTitleDTO").setQuery("dummyQueryDTO");
    }

    @Test
    public void getObjectTest() {
        given(timelineRepository.findAll()).willReturn(timelineList);
        List<TimelineDTO> list = timelineService.getObject();
        assertThat(list).isNotNull();
    }

    @Test
    public void getObjectByIdTest(){
        given(timelineRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(timeline));
        TimelineDTO dto = timelineService.getObject("1");
    }

    @Test
    public void getObjectByIdWhenEmptyTest(){
        given(timelineRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            timelineService.getObject("1");
        });

        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void postObjectTest(){
        given(timelineMapper.map(ArgumentMatchers.any(TimelineDTO.class))).willReturn(timeline);
        given(timelineRepository.save(ArgumentMatchers.any(Timeline.class))).willReturn(timeline);
        timelineService.postObject(timelineDTO);
    }

    @Test
    public void getDeleteByIdTest(){
        given(timelineRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(timeline));
        timelineService.deleteObject("1");

    }

    @Test
    public void getDeleteByIdWhenEmptyTest(){
        given(timelineRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            timelineService.deleteObject("1");
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,expectedMessage);
    }





}
