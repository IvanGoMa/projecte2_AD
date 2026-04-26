package ivha.jpa.project2.DTO;

public class UserRequestDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean status;

    public UserRequestDTO(String email, String password, String firstName, String lastName, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public UserRequestDTO(String email, String password, String firstName, Boolean status, String lastName, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.status = status;
        this.lastName = lastName;
        this.phone = phone;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    

    
}
