package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepo {

    private Map<String, Order> repo;

    @Autowired
    public OrderRepo() {
        this.repo = new ConcurrentHashMap<>();
    }

    public Order add(Order order) {
        repo.put(order.getId(), order);
        return order;
    }

    public boolean contains(Order order) {
        return repo.containsValue(order);
    }

    public Map<String, Order> getRepo() {
        return repo;
    }

    public List<Order> getAllOrders() {
        return repo.values().stream().toList();
    }

    public Order get(String orderId) {
        return repo.get(orderId);
    }
}
