package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.ItemGroup;
import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepoTest {

    private final OrderRepo repo = new OrderRepo();

    @Test
    void addMethodAndContainsMethodWork() {
        //given
        Address address = new Address("Street", "25", "1000", "Bxl");
        Customer customer = new Customer().setFirstName("A")
                .setLastName("B")
                .setAddress(address)
                .setEmail("a@b.com")
                .setPhoneNumber("012345678");
        ItemDto dto = new ItemDto("123", "A", "V", 10, 250);
        ItemGroup itemGroup = new ItemGroup(dto.getId(), 5);
        List<ItemGroup> itemGroups = List.of(itemGroup);
        Order order1 = new Order(customer.getId(), itemGroups);
        Order order2 = new Order(customer.getId(), itemGroups);
        //when
        repo.add(order1);
        //then
        assertTrue(repo.contains(order1));
        assertFalse(repo.contains(order2));
    }
}