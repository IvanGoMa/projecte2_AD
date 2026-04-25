package ivha.jpa.project2.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue 
    private int id;
    @Column(nullable=false, length=100)
    private String email;
    @Column(length=20)
    private String password;
    private boolean status; 
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    
    
    public User() {
    }

    //constructor
    public User(String email, String password, boolean status, Timestamp dateCreated, Timestamp dateUpdated) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    // getters i setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }
    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
    public List<Role> getRols() {
        return rols;
    }

    public void setRols(List<Role> rols) {
        this.rols = rols;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        // Afegim user a customer, ja que customer es el costat propietari de la fk
        if (customer != null){
            customer.setUser(this);
        }
        
    }



    // ManyToMany - rol de usuari, en el costat del propietari
    @ManyToMany
    @JoinTable(
        name = "user_roles", // taula auxiliar
        joinColumns = @JoinColumn(name = "userId" ), // propietari
        inverseJoinColumns = @JoinColumn(name = "roleId") // invers
    )
    private List<Role> rols = new ArrayList<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;
}
