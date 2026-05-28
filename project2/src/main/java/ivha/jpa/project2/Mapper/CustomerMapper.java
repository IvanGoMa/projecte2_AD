package ivha.jpa.project2.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.AddressResponseDTO;
import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.Model.Address;
import ivha.jpa.project2.Model.Customer;

@Component
public class CustomerMapper {

    public CustomerResponseDTO toCustomerResponseDTO(Customer c){
        List<AddressResponseDTO> addresses = new ArrayList<>();
        for (Address a : c.getAdresses()){
            addresses.add(new AddressResponseDTO(
                a.getAddress(), a.getCity(), a.getPostalCode(), 
                a.getCountry(), a.isDefault()
            ));
        }
        CustomerResponseDTO customer = new CustomerResponseDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getPhone(), addresses);
        // Afegim el DTO de User
        if (c.getUser() != null){
            UserResponseDTO user = new UserResponseDTO(c.getUser().getEmail(), c.getUser().getId());
            customer.setUser(user);
        }
        return customer;
    }

    public CustomerResponseDTO toCustomerUserEmailResponseDTO(Customer c){
        List<AddressResponseDTO> addresses = new ArrayList<>();
        for (Address a : c.getAdresses()){
            addresses.add(new AddressResponseDTO(
                a.getAddress(), a.getCity(), a.getPostalCode(), 
                a.getCountry(), a.isDefault()
            ));
        }
        CustomerResponseDTO customer = new CustomerResponseDTO(
            c.getId(), c.getFirstName(), c.getLastName(), c.getPhone(), addresses
        );
        if (c.getUser() != null){
            UserResponseDTO user = new UserResponseDTO(c.getUser().getEmail(), c.getUser().getId());
            customer.setUser(user);
        }
        return customer;
    }
    
}
