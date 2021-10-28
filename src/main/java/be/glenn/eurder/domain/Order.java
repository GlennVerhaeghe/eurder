package be.glenn.eurder.domain;


import java.util.ArrayList;
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
        this.totalPrice = calculateOrderPrice();
    }

    public double calculateOrderPrice() {
        return orderedItems.stream()
                .map(ItemGroup::getItemDto)
                .mapToDouble(dto -> dto.getAmount() * dto.getPrice())
                .sum();
    }

    public boolean allInputIsValid() {
        return customerId != null
                && orderedItems != null
                && orderedItems.size() > 0;
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
}
