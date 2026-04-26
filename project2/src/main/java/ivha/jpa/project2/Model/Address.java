package ivha.jpa.project2.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private int id;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private Boolean isDefault;
    
    public Address() {
    }

    public Address(String address, String city, String postalCode, String country, Boolean isDefault) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.isDefault = isDefault;
    }

    // N:1 amb Customer (la adressa guarda el id del customer)
    @ManyToOne // propietari, guardara la fk.
    @JoinColumn(name = "customerId") // es crea la columna
    private Customer customer;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Boolean getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Customer getCustomer(){
        return customer;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    
}
