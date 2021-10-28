package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.ItemGroup;
import be.glenn.eurder.domain.dtos.CreateItemGroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemGroupMapper {

    public ItemGroup createDtoToItemGroup(CreateItemGroupDto dto) {
        return new ItemGroup(dto.getItemId(), dto.getAmount());
    }

    public CreateItemGroupDto itemGroupToCreateItemGroupDto(ItemGroup ig) {
        return new CreateItemGroupDto(ig.getItemId(), ig.getAmount());
    }

    public List<ItemGroup> createDtoListToItemGroupList(List<CreateItemGroupDto> list) {
        return list.stream().map(this::createDtoToItemGroup).collect(Collectors.toList());
    }

    public List<CreateItemGroupDto> itemGroupListToCreateItemGroupDtoList(List<ItemGroup> list) {
        return list.stream().map(this::itemGroupToCreateItemGroupDto).collect(Collectors.toList());
    }
}
