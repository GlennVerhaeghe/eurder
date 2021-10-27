package be.glenn.eurder.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateItemDtoTest {

    @Test
    void givenACompleteUpdateItemDtoTheUpdateItemDtoIsValid() {
        //given
        UpdateItemDto dto = new UpdateItemDto("12345", "A", "B", 10, 5);
        //then
        assertTrue(dto.allInputIsValid());
    }

    @Test
    void givenAnUpdateItemDtoWithNoIdTheUpdateItemDtoIsInvalid() {
        //given
        UpdateItemDto dto = new UpdateItemDto(null, "A", "B", 10, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenAnUpdateItemDtoWithNoNameTheUpdateItemDtoIsInvalid() {
        //given
        UpdateItemDto dto = new UpdateItemDto("12345", null, "B", 10, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenAnUpdateItemDtoWithNoDescriptionTheUpdateItemDtoIsInvalid() {
        //given
        UpdateItemDto dto = new UpdateItemDto("12345", "A", null, 10, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenAnUpdateItemDtoWithPriceZeroTheUpdateItemDtoIsInvalid() {
        //given
        UpdateItemDto dto = new UpdateItemDto("12345", "A", "B", 0, 5);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenAnUpdateItemDtoWithNegativeAmountTheUpdateItemDtoIsInvalid() {
        //given
        UpdateItemDto dto = new UpdateItemDto("12345", "A", "B", 10, -5);        //then
        assertFalse(dto.allInputIsValid());
    }

}