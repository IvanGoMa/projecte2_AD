package ivha.jpa.project2.DTO;

public class OrderItemRequestDTO {
    private int quantity;
    private float unitPrice;
    private int productId;

    

    public OrderItemRequestDTO(int quantity, float unitPrice, int productId) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productId = productId;
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
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    

}
