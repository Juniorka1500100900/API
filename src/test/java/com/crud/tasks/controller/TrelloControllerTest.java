package com.crud.tasks.controller;

import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TrelloControllerTest {

    @Mock
    private TrelloFacade trelloFacade;

    @InjectMocks
    private TrelloController trelloController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTrelloBoards_shouldReturnTrelloBoardDtoList() {
        // Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("boardId", "Test Board", (ArrayList<Object>) new ArrayList<>()));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // When
        ResponseEntity<List<TrelloBoardDto>> responseEntity = trelloController.getTrelloBoards();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trelloBoards, responseEntity.getBody());
    }

    @Test
    void createTrelloCard_shouldReturnCreatedTrelloCardDto() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardName", "CardDescription", "CardPos", "ListId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "CardName", "CardUrl");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // When
        ResponseEntity<CreatedTrelloCardDto> responseEntity = trelloController.createTrelloCard(trelloCardDto);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdTrelloCardDto, responseEntity.getBody());
    }
}
