package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.CreateItemDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import be.glenn.eurder.domain.dtos.UpdateItemDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemMapperTest {

    private ItemMapper mapper;

    @BeforeAll
    void setup() {
        this.mapper = new ItemMapper();
    }

    @Test
    void createDtoToItemWorks() {
        //given
        CreateItemDto dto = new CreateItemDto("A", "B", 10, 5);
        //when
        Item item = mapper.createDtoToItem(dto);
        //then
        assertEquals(dto.getName(), item.getName());
        assertEquals(dto.getDescription(), item.getDescription());
        assertEquals(dto.getPrice(), item.getPrice());
        assertEquals(dto.getAmount(), item.getAmount());
        assertNotNull(item.getId());
    }

    @Test
    void itemToDtoWorks() {
        //given
        Item item = new Item().setName("A")
                .setDescription("B")
                .setPrice(10)
                .setAmount(5);
        //when
        ItemDto dto = mapper.itemToDto(item);
        //then
        assertEquals(item.getId(), dto.getId());
        assertEquals(item.getName(), dto.getName());
        assertEquals(item.getDescription(), dto.getDescription());
        assertEquals(item.getPrice(), dto.getPrice());
        assertEquals(item.getAmount(), dto.getAmount());
    }

    @Test
    void createDtoToDtoWorks() {
        //given
        CreateItemDto dto = new CreateItemDto("A", "B", 10, 5);
        //when
        ItemDto result = mapper.createDtoToDto(dto);
        //then
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getPrice(), result.getPrice());
        assertEquals(dto.getAmount(), result.getAmount());
        assertNotNull(result.getId());
    }

    @Test
    void dtoToItemWorks() {
        //given
        ItemDto dto = new ItemDto("123", "A", "B", 10, 5);
        //when
        Item item = mapper.dtoToItem(dto);
        //then
        assertEquals(dto.getId(), item.getId());
        assertEquals(dto.getName(), item.getName());
        assertEquals(dto.getDescription(), item.getDescription());
        assertEquals(dto.getPrice(), item.getPrice());
        assertEquals(dto.getAmount(), item.getAmount());
    }

    @Test
    void updateDtoToItemWorks() {
        //given
        UpdateItemDto dto = new UpdateItemDto("12345", "A", "B", 10, 5);
        //when
        Item item = mapper.updateDtoToItem(dto);
        //then
        assertEquals(dto.getId(), item.getId());
        assertEquals(dto.getName(), item.getName());
        assertEquals(dto.getDescription(), item.getDescription());
        assertEquals(dto.getPrice(), item.getPrice());
        assertEquals(dto.getAmount(), item.getAmount());
    }

}