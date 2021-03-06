package neo.com.sqllite_demo;


import java.io.Serializable;

public class ProductEntity implements Serializable {
    private int id;
    private String name;
    private int price;
    private String description;

    public ProductEntity(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductEntity(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
