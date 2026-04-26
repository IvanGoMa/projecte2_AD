package ivha.jpa.project2.DTO;

import java.util.List;

import ivha.jpa.project2.Model.Address;

public class CustomerResponseDTO {
    
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private UserResponseDTO user;
    private List<Address> addresses;
    private String userEmail;

    

    public CustomerResponseDTO(int id, String firstName, String lastName, String phone, List<Address> addresses, UserResponseDTO user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.user = user;
        this.addresses = addresses;
    }

    
    // Customer sense user per evitar recursivitat
    public CustomerResponseDTO(int id, String firstName, String lastName, String phone, List<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.addresses = addresses;
    }

    // Constructor con el email del user
    public CustomerResponseDTO(int id, String firstName, String lastName, String phone, List<Address> addresses, String userEmail) {
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



    public List<Address> getAddresses() {
        return addresses;
    }



    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    

    

}
