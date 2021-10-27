package be.glenn.eurder.domain.dtos;

public class UpdateItemDto {

    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int amount;

    public UpdateItemDto(String id, String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public boolean allInputIsValid() {
        return id != null
                && name != null
                && description != null
                && price > 0
                && amount >= 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
