package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateItemGroupDto;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    private final OrderMapper mapper = new OrderMapper(new ItemGroupMapper());
    private final Address address = new Address("Street", "25", "1000", "Bxl");
    private final Customer customer = new Customer().setFirstName("A")
            .setLastName("B")
            .setAddress(address)
            .setEmail("a@b.com")
            .setPhoneNumber("012345678");
    private final ItemDto dto = new ItemDto("123", "A", "V", 10, 250);
    private final CreateItemGroupDto itemGroup = new CreateItemGroupDto(dto.getId(), 5);
    private final List<CreateItemGroupDto> itemGroups = List.of(itemGroup);

    @Test
    void createOrderDtoToDtoWorks() {
        //given
        CreateOrderDto dto = new CreateOrderDto(customer.getId(), itemGroups);
        //when
        Order order = mapper.createOrderDtoToOrder(dto);
        //then
        assertEquals(dto.getCustomerId(), order.getCustomerId());
        assertNotNull(order.getId());
    }

}