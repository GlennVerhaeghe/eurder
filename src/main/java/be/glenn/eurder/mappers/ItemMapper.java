package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.CreateItemDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import be.glenn.eurder.domain.dtos.UpdateItemDto;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {
    public Item createDtoToItem(CreateItemDto dto) {
        return new Item().setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPrice(dto.getPrice())
                .setAmount(dto.getAmount());
    }

    public Item dtoToItem(ItemDto dto) {
        return new Item(dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getAmount());
    }

    public ItemDto itemToDto(Item item) {
        return new ItemDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getAmount());
    }

    public ItemDto createDtoToDto(CreateItemDto dto) {
        return itemToDto(createDtoToItem(dto));
    }

    public Item updateDtoToItem(UpdateItemDto dto) {
        return new Item(dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getAmount());
    }
}
