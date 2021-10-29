package be.glenn.eurder.api;


import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.CreateOrderDto;
import be.glenn.eurder.domain.dtos.OrderDto;
import be.glenn.eurder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody CreateOrderDto dto) {
        return service.createOrder(dto);
    }

    @PostMapping(produces = "application/json", consumes = "application/json", path = "/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reOrder(@PathVariable String orderId) { return service.reOrder(orderId); }
}
