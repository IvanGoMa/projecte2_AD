package ivha.jpa.project2.Mapper;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.AddressRequestDTO;
import ivha.jpa.project2.DTO.AddressResponseDTO;
import ivha.jpa.project2.Model.Address;

@Component
public class AddressMapper {

    public Address toAddress(AddressRequestDTO request){
        Address address = new Address(
            request.getAddress(), request.getCity(), request.getPostalCode(), 
            request.getCountry(), request.isDefault()
        );
        return address;
    }

    public AddressResponseDTO toResponseDTO(Address a){
        AddressResponseDTO response = new AddressResponseDTO(
            a.getAddress(), a.getCity(), a.getPostalCode(), 
            a.getCountry(), a.isDefault()
        );
        return response;
    }

}
