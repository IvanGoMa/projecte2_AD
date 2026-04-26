package ivha.jpa.project2.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean status;
    private Timestamp dataCreated;
    private Timestamp dataUpdated;

    public Customer() {
    }
    public Customer(String firstName, String lastName, String phone, boolean status, Timestamp dataCreated,
            Timestamp dataUpdated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
        this.dataCreated = dataCreated;
        this.dataUpdated = dataUpdated;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Timestamp getDataCreated() {
        return dataCreated;
    }
    public void setDataCreated(Timestamp dataCreated) {
        this.dataCreated = dataCreated;
    }
    public Timestamp getDataUpdated() {
        return dataUpdated;
    }
    public void setDataUpdated(Timestamp dataUpdated) {
        this.dataUpdated = dataUpdated;
    }


    public List<Address> getAdresses() {
        return adresses;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setAdresses(List<Address> adresses) {
        this.adresses = adresses;
    }
    




    //1:N amb address (El Customer guarda la llista d'adreces)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> adresses = new ArrayList<>();
    
    //1:N amb order (El Customer guarda la llista d'ordres)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    //1:1 amb user (customer guarda el fk d'usuari, si s'elimina customer l'usuari existeix)
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    

}
