package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepo {

    private final Map<String, Item> repo;

    @Autowired
    public ItemRepo() {
        this.repo = new ConcurrentHashMap<>();
        repo.put("123", new Item("123", "Name", "Desc", 10.0, 100));
    }

    public Item add(Item item) {
        repo.put(item.getId(), item);
        return item;
    }

    public Item get(String itemId) {
        return repo.get(itemId);
    }

    public boolean contains(Item item) {
        return repo.containsValue(item);
    }

    public boolean containsKey(String itemId) { return repo.containsKey(itemId);}

    public List<Item> getAllItems() {
        return repo.values().stream().toList();
    }
}
