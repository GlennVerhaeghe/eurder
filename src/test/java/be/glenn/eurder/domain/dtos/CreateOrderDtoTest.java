package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.ItemGroup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderDtoTest {

    private final Address address = new Address("Street", "25", "1000", "Bxl");
    private final Customer customer = new Customer().setFirstName("A")
            .setLastName("B")
            .setAddress(address)
            .setEmail("a@b.com")
            .setPhoneNumber("012345678");
    private final ItemDto dto = new ItemDto("123", "A", "V", 10, 250);
    private final CreateItemGroupDto itemGroup = new CreateItemGroupDto(dto.getId(), 5);

    @Test
    void givenNoOrderedItemsAnOrderIsInvalid() {
        //given
        CreateOrderDto dto = new CreateOrderDto("12345", null);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenNoCustomerIdAnOrderIsInvalid() {
        //given
        List<CreateItemGroupDto> itemGroups = List.of(itemGroup);
        CreateOrderDto dto = new CreateOrderDto(null, itemGroups);
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenValidInputACreateOrderDtoIsValid() {
        //given
        List<CreateItemGroupDto> itemGroups = List.of(itemGroup);
        CreateOrderDto dto = new CreateOrderDto(customer.getId(), itemGroups);
        //then
        assertTrue(dto.allInputIsValid());
    }


}