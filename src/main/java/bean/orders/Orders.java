package bean.orders;

public class Orders {
    private Integer orderID;
    private  Integer CustomerID;
    private Integer productID;
    private String dates;
    private Integer amount;
    private Integer quantity;
    private Integer status;

    public Orders() {

    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getCustomerID() { return CustomerID; }

    public void setCustomerID(Integer customerID) { CustomerID = customerID; }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getDates() { return dates; }

    public void setDates(String dates) { this.dates = dates; }

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
