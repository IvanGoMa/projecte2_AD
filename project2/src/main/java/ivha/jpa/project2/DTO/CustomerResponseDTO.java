package ivha.jpa.project2.DTO;

import java.util.List;


public class CustomerResponseDTO {
    
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private UserResponseDTO user;
    private List<AddressResponseDTO> addresses;
    private String userEmail;

    
    // Customer sense user per evitar recursivitat
    public CustomerResponseDTO(int id, String firstName, String lastName, String phone, List<AddressResponseDTO> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.addresses = addresses;
    }

    public CustomerResponseDTO(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Constructor con el email del user
    public CustomerResponseDTO(int id, String firstName, String lastName, String phone, List<AddressResponseDTO> addresses, String userEmail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.addresses = addresses;
        this.userEmail = userEmail;
    }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }



    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }
    public UserResponseDTO getUser() {
        return user;
    }



    public void setId(int id) {
        this.id = id;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }



    public void setUser(UserResponseDTO user) {
        this.user = user;
    }



    public List<AddressResponseDTO> getAddresses() {
        return addresses;
    }



    public void setAddresses(List<AddressResponseDTO> addresses) {
        this.addresses = addresses;
    }
    

    

}
