package be.glenn.eurder.domain.dtos;


import java.util.List;

public class CreateOrderDto {

    private final String customerId;
    private final List<CreateItemGroupDto> orderedItems;

    public CreateOrderDto(String customerId, List<CreateItemGroupDto> orderedItems) {
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

    public List<CreateItemGroupDto> getOrderedItems() {
        return orderedItems;
    }
}
