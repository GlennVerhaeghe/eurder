package be.glenn.eurder.services;

import be.glenn.eurder.domain.dtos.ReportDto;
import be.glenn.eurder.mappers.OrderMapper;
import be.glenn.eurder.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    @Autowired
    public ReportService(OrderRepo orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    public ReportDto getReportsByCustomerId(String customerId) {
        return new ReportDto(orderMapper.orderListToOrderDtoList(orderRepo.getOrdersByCustomer(customerId)));
    }
}
