package be.glenn.eurder.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateItemDtoTest {

    @Test
    void givenACompleteCreateItemDtoTheCreateItemDtoIsValid() {
        //given
        CreateItemDto dto = new CreateItemDto("A", "B", 10, 5);
        //then
        assertTrue(dto.allInputIsValid());
    }

    @Test
    void givenACreateItemDtoWithNoNameTheCreateItemDtoIsInvalid() {
        //given
        CreateItemDto dto = new CreateItemDto(null, "B", 10, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenACreateItemDtoWithNoDescriptionTheCreateItemDtoIsInvalid() {
        //given
        CreateItemDto dto = new CreateItemDto("A", null, 10, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenACreateItemDtoWithPriceZeroTheCreateItemDtoIsInvalid() {
        //given
        CreateItemDto dto = new CreateItemDto("A", "B", 0, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenACreateItemDtoWithNegativeAmountTheCreateItemDtoIsInvalid() {
        //given
        CreateItemDto dto = new CreateItemDto("A", "B", 10, -5);
        //then
        assertFalse(dto.allInputIsValid());
    }

}