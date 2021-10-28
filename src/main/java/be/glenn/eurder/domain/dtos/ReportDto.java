package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.Order;

import java.util.Map;

public class ReportDto {

    private final Map<String, Order> orders;
    private final double totalPrice;

        public ReportDto(Map<String, Order> orders) {
        this.orders = orders;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return orders.values().stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public ReportDto addItemGroup(Order order) {
        orders.put(order.getId(), order);
        return this;
    }

    public Map<String, Order> getOrders() {
        return orders;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
