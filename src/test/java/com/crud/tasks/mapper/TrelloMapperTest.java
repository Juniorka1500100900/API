package com.crud.tasks.mapper;
import com.crud.tasks.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TrelloMapperTest {
    private TrelloMapper trelloMapper;

    @BeforeEach
    void setUp() {
        trelloMapper = new TrelloMapper();
    }

    @Test
    void mapToBoards_shouldMapToBoardsCorrectly() {
        // Given
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(
                new TrelloBoardDto("boardId1", "boardName1", Arrays.asList(new TrelloListDto("listId1", "listName1", false))),
                new TrelloBoardDto("boardId2", "boardName2", Arrays.asList(new TrelloListDto("listId2", "listName2", true)))
        );

        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        // Then
        assertEquals(2, trelloBoards.size());
        assertEquals("boardId1", trelloBoards.get(0).getId());
        assertEquals("boardName1", trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
    }

    @Test
    void mapToBoardsDto_shouldMapToBoardsDtoCorrectly() {
        // Given
        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("boardId1", "boardName1", Arrays.asList(new TrelloList("listId1", "listName1", false))),
                new TrelloBoard("boardId2", "boardName2", Arrays.asList(new TrelloList("listId2", "listName2", true)))
        );

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        // Then
        assertEquals(2, trelloBoardDtos.size());
        assertEquals("boardId1", trelloBoardDtos.get(0).getId());
        assertEquals("boardName1", trelloBoardDtos.get(0).getName());
        assertEquals(1, trelloBoardDtos.get(0).getLists().size());
    }

    @Test
    void mapToList_shouldMapToListCorrectly() {
        // Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("listId1", "listName1", false),
                new TrelloListDto("listId2", "listName2", true)
        );

        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        // Then
        assertEquals(2, trelloLists.size());
        assertEquals("listId1", trelloLists.get(0).getId());
        assertEquals("listName1", trelloLists.get(0).getName());
        assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    void mapToListDto_shouldMapToListDtoCorrectly() {
        // Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("listId1", "listName1", false),
                new TrelloList("listId2", "listName2", true)
        );

        // When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        // Then
        assertEquals(2, trelloListDtos.size());
        assertEquals("listId1", trelloListDtos.get(0).getId());
        assertEquals("listName1", trelloListDtos.get(0).getName());
        assertEquals(false, trelloListDtos.get(0).isClosed());
    }

    @Test
    void testMapToCardDto_shouldMapToCardDtoCorrectly() {
        TrelloCard trelloCard = new TrelloCard("CardName", "CardDescription", "CardPos", "ListId");

        System.out.println("Before mapping: " + trelloCard);

        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        System.out.println("After mapping: " + trelloCardDto);
    }
    @Test
    void mapToCard_shouldMapToCardCorrectly() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardName", "CardDescription", "CardPos", "ListId");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertNotNull(trelloCard);
        assertEquals("CardName", trelloCard.getName());
        assertEquals("CardDescription", trelloCard.getDescription());
        assertEquals("CardPos", trelloCard.getPos());
        assertEquals("ListId", trelloCard.getListId());
    }
}