package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order createOrderDtoToOrder(CreateOrderDto dto) {
        return new Order(dto.getCustomerId(), dto.getOrderedItems());
    }
}
