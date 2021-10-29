package be.glenn.eurder.services;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.*;
import be.glenn.eurder.mappers.ItemGroupMapper;
import be.glenn.eurder.mappers.OrderMapper;
import be.glenn.eurder.repos.CustomerRepo;
import be.glenn.eurder.repos.ItemRepo;
import be.glenn.eurder.repos.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opendope.conditions.Or;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportServiceTest {

    private ReportService reportService;
    private OrderMapper mapper;
    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private ItemRepo itemRepo;
    private OrderService orderService;
    private ItemGroupMapper itemGroupMapper;

    private Address address;
    private Customer customer;
    private ItemDto itemDto;
    private List<CreateItemGroupDto> itemGroups;
    private CreateOrderDto createOrderDto;
    private Item item;
    private OrderDto dto;

    @BeforeEach
    void setup() {
        orderRepo = new OrderRepo();
        reportService = new ReportService(orderRepo, mapper);
        mapper = new OrderMapper();
        customerRepo = new CustomerRepo();
        itemRepo = new ItemRepo();
        itemGroupMapper = new ItemGroupMapper();

        orderService = new OrderService(orderRepo, customerRepo, itemRepo, mapper, itemGroupMapper);
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

        itemRepo.add(item);
        customerRepo.add(customer);
        dto = orderService.createOrder(createOrderDto);
    }

    @Test
    void reportingWorks() {
        //when
        ReportDto report = reportService.getReportsByCustomerId(customer.getId());
        OrderDto dto2 = new OrderDto("123", "123", itemGroupMapper.createDtoListToItemGroupList(itemGroups), 25);
        //then
        assertEquals(report.getOrders().get(0), dto);
        assertNotEquals(report.getOrders().get(0), dto2);
    }
}