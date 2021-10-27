package be.glenn.eurder.services;

import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.CreateItemDto;
import be.glenn.eurder.domain.dtos.UpdateItemDto;
import be.glenn.eurder.exceptions.AuthorisationException;
import be.glenn.eurder.mappers.ItemMapper;
import be.glenn.eurder.repos.ItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceTest {

    private ItemMapper mapper;
    private ItemRepo repo;
    private ItemService service;

    @BeforeEach
    void setup() {
        mapper = new ItemMapper();
        repo = new ItemRepo();
        service = new ItemService(repo, mapper);
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class createItemTesting {
        @Test
        void createItemWithValidDataResultsInTheCreationOfAnItem() {
            //given
            CreateItemDto dto = new CreateItemDto("A", "B", 10, 5);
            //when
            Item item = service.createItem(dto, "666");
            //then
            assertTrue(repo.contains(item));
        }

        @Test
        void createItemWithInvalidDataThrowsException() {
            //given
            CreateItemDto dto = new CreateItemDto("A", "B", 10, -5);
            //then
            assertThrows(IllegalArgumentException.class, () -> service.createItem(dto, "666"));
        }

        @Test
        void createItemWithoutAdminIdThrowsException() {
            //given
            CreateItemDto dto = new CreateItemDto("A", "B", 10, 5);
            //then
            assertThrows(AuthorisationException.class, () -> service.createItem(dto, "wrong id"));
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class updateItemTesting {

        @Test
        void updateItemWithInvalidDataThrowsException() {
            //given
            UpdateItemDto dto = new UpdateItemDto(null, "A", "B", 10, -5);
            //then
            assertThrows(IllegalArgumentException.class, () -> service.updateItem(dto, "666"));
        }

        @Test
        void updateItemWithoutAdminIdThrowsException() {
            //given
            UpdateItemDto dto = new UpdateItemDto("123", "A", "B", 10, 5);
            //then
            assertThrows(AuthorisationException.class, () -> service.updateItem(dto, "wrong id"));
        }

        @Test
        void updateItemOnANonExistingItemThrowsException() {
            //given
            UpdateItemDto dto = new UpdateItemDto("012", "A", "B", 10, 5);
            //then
            assertThrows(IllegalArgumentException.class, () -> service.updateItem(dto, "666"));
        }

        @Test
        void updateItemWithAllValidInputsWorks() {
            //given
            Item item = new Item().setName("A").setDescription("B").setPrice(20).setAmount(50);
            UpdateItemDto dto = new UpdateItemDto(item.getId(), "A", "B", 10, 5);
            //when
            repo.add(item);
            service.updateItem(dto, "666");
            //then
            assertEquals(repo.get(item.getId()), mapper.updateDtoToItem(dto));
        }
    }



}