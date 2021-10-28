package be.glenn.eurder.services;

import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.mappers.OrderMapper;
import be.glenn.eurder.repos.CustomerRepo;
import be.glenn.eurder.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final OrderMapper mapper;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, OrderMapper mapper) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.mapper = mapper;
    }

    public Order createOrder(CreateOrderDto dto) {
        if(!dto.allInputIsValid()) {
            throw new IllegalArgumentException("Not all required input to create an order is given");
        }
        if(!customerRepo.containsKey(dto.getCustomerId())) {
            throw new IllegalArgumentException("Customer is not registered in our database.");
        }
        return orderRepo.add(mapper.createOrderDtoToOrder(dto));
    }
}
