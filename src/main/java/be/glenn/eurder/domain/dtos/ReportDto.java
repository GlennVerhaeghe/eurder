package be.glenn.eurder.domain.dtos;

import java.util.List;

public class ReportDto {

    private final List<OrderDto> orders;
    private final double totalPrice;

    public ReportDto(List<OrderDto> orders) {
        this.orders = orders;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return orders.stream()
                .mapToDouble(OrderDto::getTotalPrice)
                .sum();
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
