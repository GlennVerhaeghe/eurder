package be.glenn.eurder.domain;

import java.util.List;
import java.util.UUID;

public class Order {

    private final String customerId;
    private final List<ItemGroup> orderedItems;
    private final String id;
    private double totalPrice;

    public Order(String customerId, List<ItemGroup> orderedItems) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.orderedItems = orderedItems;
        this.totalPrice = calculateTotalPrice();
    }

    public boolean allInputIsValid() {
        return customerId != null
                && orderedItems != null
                && orderedItems.size() > 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getOrderedItems() {
        return orderedItems;
    }

    public double calculateTotalPrice() {
        return orderedItems.stream()
                .mapToDouble(ItemGroup::getTotalPrice)
                .sum();
    }
}
