package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.ItemGroup;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
