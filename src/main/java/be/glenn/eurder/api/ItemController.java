package be.glenn.eurder.api;

import be.glenn.eurder.domain.Item;
import be.glenn.eurder.domain.dtos.CreateItemDto;
import be.glenn.eurder.domain.dtos.ItemDto;
import be.glenn.eurder.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody CreateItemDto dto, @RequestHeader String id) {
        return itemService.createItem(dto, id);
    }

}
