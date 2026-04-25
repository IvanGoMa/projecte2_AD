package ivha.jpa.project2.DTO;

public class OrderItemResponseDTO {

    private int quantity;
    private float unitPrice;
    private productResponseDTO product;

    

    public OrderItemResponseDTO(int quantity, float unitPrice, productResponseDTO product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
    }

    // OrderItem sense product per evitar recursivitat
    public OrderItemResponseDTO(int quantity, float unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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
    public productResponseDTO getProduct() {
        return product;
    }
    public void setProduct(productResponseDTO product) {
        this.product = product;
    }

    

}
