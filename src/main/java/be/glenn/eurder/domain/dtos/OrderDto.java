package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.ItemGroup;

import java.util.List;

public class OrderDto {

    private final String customerId;
    private final List<ItemGroup> orderedItems;
    private final String id;
    private final double totalPrice;

    public OrderDto(String id, String customerId, List<ItemGroup> orderedItems, double totalPrice) {
        this.customerId = customerId;
        this.orderedItems = orderedItems;
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getOrderedItems() {
        return orderedItems;
    }

    public String getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
