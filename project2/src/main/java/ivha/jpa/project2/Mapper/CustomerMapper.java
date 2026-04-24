package ivha.jpa.project2.Mapper;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.Model.Customer;

@Component
public class CustomerMapper {

    public CustomerResponseDTO toCustomerResponseDTO(Customer c){
        CustomerResponseDTO customer = new CustomerResponseDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getPhone(), c.getAdresses());
        if (customer.getUser() != null){
            UserResponseDTO user = new UserResponseDTO(customer.getUser().getEmail(), customer.getUser().getId());
            customer.setUser(user);
        }
        return customer;
    }
}
