package bean.order;

public class Order {
    private  Integer customerID;
    private Integer productID;
    private String dates;
    private Integer amount;
    private Integer quantity;
    private Integer status;

    public Order(String dates) {
        this.dates = dates;
    }

    public Integer getCustomerID() { return customerID; }

    public void setCustomerID(Integer customerID) { this.customerID = customerID; }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getDates() { return dates; }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }
}
