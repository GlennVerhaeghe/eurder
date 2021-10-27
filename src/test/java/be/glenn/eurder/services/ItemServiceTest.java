package be.glenn.eurder.services;

import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.CreateItemDto;
import be.glenn.eurder.mappers.ItemMapper;
import be.glenn.eurder.repos.ItemRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceTest {

    private ItemMapper mapper;
    private ItemRepo repo;
    private ItemService service;

    @BeforeAll
    void setup() {
        mapper = new ItemMapper();
        repo = new ItemRepo();
        service = new ItemService(repo, mapper);
    }

    @Test
    void createItemWithValidDataResultsInTheCreationOfAnItem() {
        //given
        CreateItemDto dto = new CreateItemDto("A", "B", 10, 5);
        //when
        Item item = service.createItem(dto, "666");
        //then
        assertTrue(repo.contains(item));
    }

}