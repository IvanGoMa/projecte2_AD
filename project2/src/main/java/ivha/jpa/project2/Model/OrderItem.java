package ivha.jpa.project2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    private int quantity;
    private float unitPrice;


    
    public OrderItem(Order order, Product product, int quantity, float unitPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(int quantity, float unitPrice){
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
