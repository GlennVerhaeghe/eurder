package be.glenn.eurder.services;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.ItemGroup;
import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import be.glenn.eurder.mappers.OrderMapper;
import be.glenn.eurder.repos.CustomerRepo;
import be.glenn.eurder.repos.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    private OrderMapper mapper;
    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private OrderService service;

    Address address;
    Customer customer;
    ItemDto itemDto;
    List<ItemGroup> itemGroups;
    CreateOrderDto createOrderDto;

    @BeforeEach
    void setup() {
        mapper = new OrderMapper();
        orderRepo = new OrderRepo();
        customerRepo = new CustomerRepo();
        service = new OrderService(orderRepo, customerRepo, mapper);
        address = new Address("Street", "25", "1000", "Bxl");
        customer = new Customer().setFirstName("A")
                .setLastName("B")
                .setAddress(address)
                .setEmail("a@b.com")
                .setPhoneNumber("012345678");
        itemDto = new ItemDto("123", "A", "V", 10, 250);
        itemGroups = List.of(new ItemGroup(itemDto, 5));
        createOrderDto = new CreateOrderDto(customer.getId(), itemGroups);
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class CreateOrderTesting {

        @Test
        void createOrderWithValidDataResultsInCreationOfAnOrder() {
            //when
            customerRepo.add(customer);
            Order order = service.createOrder(createOrderDto);
            //then
            assertTrue(orderRepo.contains(order));
        }

        @Test
        void createOrderWithNotRegisteredCustomerThrowsException() {
            //then
            assertThrows(IllegalArgumentException.class, () -> service.createOrder(createOrderDto));
        }
    }


}