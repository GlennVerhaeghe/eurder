package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepo {

    private final Map<String, Item> repo;

    @Autowired
    public ItemRepo() {
        this.repo = new ConcurrentHashMap<>();
    }

    public Item add(Item item) {
        repo.put(item.getId(), item);
        return item;
    }

    public boolean contains(Item item) {
        return repo.containsValue(item);
    }
}