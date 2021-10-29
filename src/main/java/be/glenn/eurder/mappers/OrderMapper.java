package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.domain.dtos.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper = new ItemGroupMapper();

    public Order createOrderDtoToOrder(CreateOrderDto dto) {
        return new Order(dto.getCustomerId(), itemGroupMapper.createDtoListToItemGroupList(dto.getOrderedItems()));
    }

    public CreateOrderDto orderToCreateOrderDto(Order order) {
        return new CreateOrderDto(order.getCustomerId(), itemGroupMapper.itemGroupListToCreateItemGroupDtoList(order.getOrderedItems()));
    }

    public OrderDto orderToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getCustomerId(), order.getOrderedItems(), order.getTotalPrice());
    }

    public List<OrderDto> orderListToOrderDtoList(List<Order> list) {
        return list.stream().map(this::orderToOrderDto).toList();
    }
}
