package be.glenn.eurder.domain;

import be.glenn.eurder.domain.dtos.ItemDto;

import java.time.LocalDate;

public class ItemGroup {

    private final ItemDto itemDto;
    private final int amount;
    private LocalDate shippingDate;

    public ItemGroup(ItemDto itemDto, int amount) {
        this.itemDto = itemDto;
        this.amount = amount;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }
}
