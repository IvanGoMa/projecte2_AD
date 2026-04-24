package ivha.jpa.project2.DTO;

public class UserResponseDTO {

    private int id;
    private String email;
    private CustomerResponseDTO customer;
    
    public UserResponseDTO(String email, int id){
        this.email = email;
        this.id = id;
    }

    public UserResponseDTO(String email, int id, CustomerResponseDTO customer){
        this.email = email;
        this.id = id;
        this.customer = customer;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerResponseDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseDTO customer) {
        this.customer = customer;
    }

    
    
}
