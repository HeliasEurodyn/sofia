package com.crm.sofia.services.sofia.download;

import com.crm.sofia.dto.sofia.download.DownloadDTO;
import com.crm.sofia.mapper.sofia.download.DownloadMapper;
import com.crm.sofia.model.sofia.download.Download;
import com.crm.sofia.repository.sofia.download.DownloadRepository;
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
public class DownloadServiceTest {


    @Mock
    private DownloadRepository downloadRepository;
    @InjectMocks
    private DownloadService downloadService;

    @Mock
    private JWTService jwtService;
    private List<Download> downloadList;

    private Download download;
    private DownloadDTO downloadDto;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private DownloadMapper downloadMapper ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        downloadList = new ArrayList<>();
        download = new Download();
        downloadDto = new DownloadDTO();
        download.setCode("1234");
        download.setName("dummy");
        downloadList.add(download);
        downloadDto.setName("124");
        downloadDto.setCode("LOTR");
    }

    @Test
    public void getObjectTest() {
        given(downloadRepository.findAll()).willReturn(downloadList);
        List<DownloadDTO> list = downloadService.getObject();
        assertThat(list).isNotNull();
    }

    @Test
    public void getObjectByIdTest(){
        given(downloadRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(download));
        DownloadDTO dto = downloadService.getObject(6L);

    }
    @Test
    public void getObjectByIdWhenEmptyTest(){
        given(downloadRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            downloadService.getObject(6L);
        });

        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void postObjectTest(){
        given(downloadRepository.save(ArgumentMatchers.any(Download.class))).willReturn(download);
        given(downloadMapper.map(ArgumentMatchers.any(DownloadDTO.class))).willReturn(download);
        downloadService.postObject(downloadDto);
    }

    @Test
    public void getDeleteByIdTest(){
        given(downloadRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(download));
        downloadService.deleteObject(6L);

    }
    @Test
    public void getDeleteByIdWhenEmptyTest(){
        given(downloadRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            downloadService.deleteObject(6L);
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,expectedMessage);
    }



}
