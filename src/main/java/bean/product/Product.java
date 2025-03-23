package bean.product;

public class Product {
    private Integer id;
    private String name;
    private String size;
    private Integer price;
    private String ice;
    private String sugar;
    private String type;
    private Integer quantity;

    public Product() {
    }

    public Product(Integer id, String name, String size, Integer price, String ice, String sugar, Integer quantity) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.ice = ice;
        this.sugar = sugar;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
