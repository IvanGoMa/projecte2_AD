package ivha.jpa.project2.DTO;

import java.sql.Timestamp;
import java.util.List;

import ivha.jpa.project2.Model.OrderStatus;

public class OrderResponseDTO {
    private int customer_id;
    private Timestamp orderDate;
    private float totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItemResponseDTO> orderItems;

    

    public OrderResponseDTO(int customer_id, Timestamp orderDate, float totalAmount, OrderStatus orderStatus) {
        this.customer_id = customer_id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public List<OrderItemResponseDTO> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemResponseDTO> orderItems) {
        this.orderItems = orderItems;
    }
    
    

}
