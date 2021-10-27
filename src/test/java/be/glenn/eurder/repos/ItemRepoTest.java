package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemRepoTest {

    ItemRepo repo = new ItemRepo();

    @Test
    void addMethodAndContainsMethodWork() {
        //given
        Item item = new Item()
                .setName("A")
                .setDescription("B")
                .setPrice(10)
                .setAmount(5);
        Item item2 = new Item()
                .setName("A")
                .setDescription("B")
                .setPrice(10)
                .setAmount(5);
        //when
        repo.add(item);
        //then
        assertTrue(repo.contains(item));
        assertFalse(repo.contains(item2));
    }
}