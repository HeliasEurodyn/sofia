package com.crm.sofia.service.search;

import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.mapper.search.SearchMapper;
import com.crm.sofia.mapper.search.SearchMapperImpl;
import com.crm.sofia.model.search.Search;
import com.crm.sofia.repository.search.SearchRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.search.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Spy
    private final SearchMapper searchMapper = new SearchMapperImpl();

    @InjectMocks
    private SearchService searchService;

    @Mock
    private SearchRepository searchRepository;


    @Mock
    private JWTService jwtService;

    private Search dto;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dto = new Search().setName("dummyName");
        dto.setId("1");

    }

    @Test
    public void getObjectTest() {
        given(searchRepository.findById(any())).willReturn(Optional.of(dto));
        SearchDTO dto = searchService.getObject("id");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getName().equals("dummyName"));

    }
}
