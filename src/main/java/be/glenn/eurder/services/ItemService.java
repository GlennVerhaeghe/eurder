package be.glenn.eurder.services;

import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.CreateItemDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import be.glenn.eurder.domain.dtos.UpdateItemDto;
import be.glenn.eurder.exceptions.AuthorisationException;
import be.glenn.eurder.mappers.ItemMapper;
import be.glenn.eurder.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepo repo;
    private final ItemMapper mapper;

    @Autowired
    public ItemService(ItemRepo repo, ItemMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ItemDto createItem(CreateItemDto dto, String id) {
        if (!AdminValidator.isAdmin(id)) {
            throw new AuthorisationException("A user without admin rights tried to add an item");
        }
        if (!dto.allInputIsValid()) {
            throw new IllegalArgumentException("Not all necessary information to create a new item was provided");
        }

        return mapper.itemToDto(repo.add(mapper.createDtoToItem(dto)));
    }

    public Item updateItem(UpdateItemDto dto, String id) {
        if (!AdminValidator.isAdmin(id)) {
            throw new AuthorisationException("A user without admin rights tried to add an item");
        }
        if (!dto.allInputIsValid()) {
            throw new IllegalArgumentException("Not all necessary information to update a new item was provided");
        }
        if (!repo.contains(mapper.updateDtoToItem(dto))) {
            throw new IllegalArgumentException("The item you want to update is not yet stored in our database");
        }
        return repo.add(mapper.updateDtoToItem(dto));
    }
}
