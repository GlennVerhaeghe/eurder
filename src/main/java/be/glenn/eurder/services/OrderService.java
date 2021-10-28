package be.glenn.eurder.services;

import be.glenn.eurder.domain.ItemGroup;
import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.domain.dtos.ItemDto;
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
    private final OrderMapper mapper;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, ItemRepo itemRepo, OrderMapper mapper) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.mapper = mapper;
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
        calculateAndSetShippingDates(dto);
        return orderRepo.add(mapper.createOrderDtoToOrder(dto));
    }

    private boolean allItemsAreInTheShop(CreateOrderDto dto) {
        return dto.getOrderedItems().stream()
                .map(ItemGroup::getItemDto)
                .map(ItemDto::getId)
                .allMatch(itemRepo::containsKey);
    }

    private void calculateAndSetShippingDates(CreateOrderDto dto) {
        dto.getOrderedItems()
                .forEach(itemGroup -> {
                    itemGroup.setShippingDate(itemGroup.getItemDto().getAmount() <= itemRepo.get(itemGroup.getItemDto().getId()).getAmount() ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7));
                });
    }
}
