package be.glenn.eurder.services;

import be.glenn.eurder.domain.*;
import be.glenn.eurder.domain.dtos.CreateItemGroupDto;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.mappers.ItemGroupMapper;
import be.glenn.eurder.mappers.OrderMapper;
import be.glenn.eurder.repos.CustomerRepo;
import be.glenn.eurder.repos.ItemRepo;
import be.glenn.eurder.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;
    private final OrderMapper orderMapper;
    private final ItemGroupMapper itemGroupMapper;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, ItemRepo itemRepo, OrderMapper orderMapper, ItemGroupMapper itemGroupMapper) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.orderMapper = orderMapper;
        this.itemGroupMapper = itemGroupMapper;
    }

    public Order createOrder(CreateOrderDto dto) {
        if (!dto.allInputIsValid()) {
            throw new IllegalArgumentException("Not all required input to create an order is given");
        }
        if (!customerRepo.containsKey(dto.getCustomerId())) {
            throw new IllegalArgumentException("Customer is not registered in our database.");
        }
        if (!allItemsAreInTheShop(dto)) {
            throw new IllegalArgumentException("We don't sell some of the items you want to buy");
        }
        Order order = orderMapper.createOrderDtoToOrder(dto);
        calculateAndSetTotalPriceForItemGroups(order);
        calculateAndSetTotalPriceForOrder(order);
        calculateAndSetShippingDates(order);
        return orderRepo.add(order);
    }

    private void calculateAndSetTotalPriceForOrder(Order order) {
        order.setTotalPrice(order.getOrderedItems().stream().mapToDouble(ItemGroup::getTotalPrice).sum());
    }

    private void calculateAndSetTotalPriceForItemGroups(Order order) {
        order.getOrderedItems().forEach(itemGroup -> itemGroup.setTotalPrice(itemGroup.getAmount() * itemRepo.get(itemGroup.getItemId()).getPrice()));
    }

    private boolean allItemsAreInTheShop(CreateOrderDto dto) {
        return dto.getOrderedItems().stream()
                .map(CreateItemGroupDto::getItemId)
                .allMatch(itemRepo::containsKey);
    }

    private void calculateAndSetShippingDates(Order order) {
        order.getOrderedItems()
                .forEach(itemGroup ->
                        itemGroup.setShippingDate(itemGroup.getAmount() <= itemRepo.get(itemGroup.getItemId()).getAmount() ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7)));
    }

    public Order reOrder(String orderId) {
        Order order = orderRepo.get(orderId);
        CreateOrderDto newOrder = new CreateOrderDto(order.getCustomerId(), itemGroupMapper.itemGroupListToCreateItemGroupDtoList(order.getOrderedItems()));
        return createOrder(newOrder);
    }
}
