package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper = new ItemGroupMapper();

    public Order createOrderDtoToOrder(CreateOrderDto dto) {
        return new Order(dto.getCustomerId(), itemGroupMapper.createDtoListToItemGroupList(dto.getOrderedItems()));
    }

    public CreateOrderDto orderToCreateOrderDto(Order order) {
        return new CreateOrderDto(order.getCustomerId(), itemGroupMapper.itemGroupListToCreateItemGroupDtoList(order.getOrderedItems()));
    }
}
