package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.ItemGroup;

import java.util.List;

public class CreateOrderDto {

    private final String customerId;
    private final List<ItemGroup> orderedItems;

    public CreateOrderDto(String customerId, List<ItemGroup> orderedItems) {
        this.customerId = customerId;
        this.orderedItems = orderedItems;
    }

    public boolean allInputIsValid() {
        return customerId != null
                && orderedItems != null
                && orderedItems.size() > 0;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getOrderedItems() {
        return orderedItems;
    }
}
