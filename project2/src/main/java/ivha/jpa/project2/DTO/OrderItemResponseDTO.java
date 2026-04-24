package ivha.jpa.project2.DTO;

import ivha.jpa.project2.Model.Product;

public class OrderItemResponseDTO {

    private int quantity;
    private float unitPrice;
    private Product product;

    

    public OrderItemResponseDTO(int quantity, float unitPrice, Product product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    

}
