package be.glenn.eurder.services;

import be.glenn.eurder.domain.*;
import be.glenn.eurder.domain.dtos.CreateItemGroupDto;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import be.glenn.eurder.domain.dtos.OrderDto;
import be.glenn.eurder.mappers.ItemGroupMapper;
import be.glenn.eurder.mappers.OrderMapper;
import be.glenn.eurder.repos.CustomerRepo;
import be.glenn.eurder.repos.ItemRepo;
import be.glenn.eurder.repos.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    private OrderMapper mapper;
    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private ItemRepo itemRepo;
    private OrderService service;
    private ItemGroupMapper itemGroupMapper;

    private Address address;
    private Customer customer;
    private ItemDto itemDto;
    private List<CreateItemGroupDto> itemGroups;
    private CreateOrderDto createOrderDto;
    private Item item;

    @BeforeEach
    void setup() {
        mapper = new OrderMapper();
        orderRepo = new OrderRepo();
        customerRepo = new CustomerRepo();
        itemRepo = new ItemRepo();
        itemGroupMapper = new ItemGroupMapper();

        service = new OrderService(orderRepo, customerRepo, itemRepo, mapper, itemGroupMapper);
        address = new Address("Street", "25", "1000", "Bxl");
        customer = new Customer().setFirstName("A")
                .setLastName("B")
                .setAddress(address)
                .setEmail("a@b.com")
                .setPhoneNumber("012345678");
        itemDto = new ItemDto("123", "A", "V", 10.0, 25);
        itemGroups = List.of(new CreateItemGroupDto(itemDto.getId(), 5));
        createOrderDto = new CreateOrderDto(customer.getId(), itemGroups);
        item = new Item("123", "A", "V", 10.0, 200);
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class CreateOrderTesting {

        @Test
        void createOrderWithValidDataResultsInCreationOfAnOrder() {
            //when
            itemRepo.add(item);
            customerRepo.add(customer);
            OrderDto dto = service.createOrder(createOrderDto);
            //then
            assertNotNull(orderRepo.get(dto.getId()));
        }

        @Test
        void createOrderWithNotRegisteredCustomerThrowsException() {
            //then
            assertThrows(IllegalArgumentException.class, () -> service.createOrder(createOrderDto));
        }

        /*@Test
        void ifAnItemIsNotInTheShopCreateOrderThrowsException() {
            //when
            customerRepo.add(customer);
            //then
            assertThrows(IllegalArgumentException.class, () -> service.createOrder(createOrderDto));
        }*/

        @Test
        void ifNotSufficientStockShippingDateGetsSetTo7DaysFromNow() {
            //when
            itemGroups = List.of(new CreateItemGroupDto(itemDto.getId(), 25));
            item = new Item("123", "A", "V", 10.0, 20);
            createOrderDto = new CreateOrderDto(customer.getId(), itemGroups);
            //when
            itemRepo.add(item);
            customerRepo.add(customer);
            OrderDto dto = service.createOrder(createOrderDto);
            //then
            assertEquals(LocalDate.now().plusDays(7), dto.getOrderedItems().get(0).getShippingDate());
        }

        @Test
        void ifSufficientStockShippingDateGetsSetTo1DayFromNow() {
            //when
            itemRepo.add(item);
            customerRepo.add(customer);
            OrderDto dto = service.createOrder(createOrderDto);
            //then
            assertEquals(LocalDate.now().plusDays(1), dto.getOrderedItems().get(0).getShippingDate());
        }
    }
}