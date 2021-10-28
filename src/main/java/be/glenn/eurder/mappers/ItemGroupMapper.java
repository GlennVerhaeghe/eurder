package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.ItemGroup;
import be.glenn.eurder.domain.dtos.CreateItemGroupDto;

import java.util.List;
import java.util.stream.Collectors;

public class ItemGroupMapper {

    public ItemGroup createDtoToItemGroup(CreateItemGroupDto dto) {
        return new ItemGroup(dto.getItemId(), dto.getAmount());
    }

    public List<ItemGroup> createDtoListToItemGroupList(List<CreateItemGroupDto> list) {
        return list.stream().map(this::createDtoToItemGroup).collect(Collectors.toList());
    }
}
