package be.glenn.eurder.services;

import be.glenn.eurder.domain.Order;
import be.glenn.eurder.domain.dtos.ReportDto;
import be.glenn.eurder.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReportService {

    private final OrderRepo orderRepo;

    @Autowired
    public ReportService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public ReportDto getReportsByCustomerId(String customerId) {
        Map<String, Order> orders = new ConcurrentHashMap<>();
        for(Order order : orderRepo.getAllOrders()) {
            if(order.getCustomerId().equals(customerId)) {
                orders.put(order.getId(), order);
            }
        }
        return new ReportDto(orders);
    }
}
