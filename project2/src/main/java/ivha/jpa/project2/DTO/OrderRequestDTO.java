package ivha.jpa.project2.DTO;

import java.sql.Timestamp;
import java.util.List;


public class OrderRequestDTO {
    private int customer_id;
    private Timestamp orderDate;
    private List<OrderItemRequestDTO> orderItems;
    private String invoiceNumber;
    private Timestamp issueDate;
    private float taxAmount;

    

    public OrderRequestDTO( int customer_id, Timestamp orderDate, List<OrderItemRequestDTO> orderItems, 
        String invoiceNumber, Timestamp issueDate, float taxAmount) {

        this.customer_id = customer_id;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.taxAmount = taxAmount;
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
    
    public List<OrderItemRequestDTO> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemRequestDTO> orderItems) {
        this.orderItems = orderItems;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public Timestamp getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }
    public float getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }
    
}
