package ivha.jpa.project2.Mapper;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.Model.Customer;

@Component
public class CustomerMapper {

    public CustomerResponseDTO toCustomerResponseDTO(Customer c){
        CustomerResponseDTO customer = new CustomerResponseDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getPhone(), c.getAdresses());
        // Afegim el DTO de User
        if (c.getUser() != null){
            UserResponseDTO user = new UserResponseDTO(c.getUser().getEmail(), c.getUser().getId());
            customer.setUser(user);
        }
        return customer;
    }
}
