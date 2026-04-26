package ivha.jpa.project2.Mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.DTO.RoleResponseDTO;
import ivha.jpa.project2.DTO.UserRequestDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.DTO.UserRolesResponseDTO;
import ivha.jpa.project2.Model.Customer;
import ivha.jpa.project2.Model.Role;
import ivha.jpa.project2.Model.User;

@Component
public class UserMapper {

    public User toUser(UserRequestDTO userRequest){

        Timestamp now = new Timestamp(System.currentTimeMillis());
        return new User(userRequest.getEmail(), userRequest.getPassword(), true, now, now);
    }

    public Customer toCustomer (UserRequestDTO userRequest){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return new Customer(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getPhone(), true, now, now);
    }

    public UserResponseDTO toUserResponseDTO(User user){
        return new UserResponseDTO(user.getEmail(), user.getId());
    }

    public UserResponseDTO toUserAndCustomerResponseDTO(User user){
        UserResponseDTO userResponse = new UserResponseDTO(user.getEmail(), user.getId());
        if (user.getCustomer() != null){
            CustomerResponseDTO customer = new CustomerResponseDTO(
                user.getCustomer().getId(),
                user.getCustomer().getFirstName(),
                user.getCustomer().getLastName(),
                user.getCustomer().getPhone(),
                user.getCustomer().getAdresses()
            );
            userResponse.setCustomer(customer);
        }
        return userResponse;
    }

    public UserRolesResponseDTO toUserRolesResponseDTO(User user){

        UserResponseDTO userResponse = new UserResponseDTO(user.getEmail(), user.getId());
        List<RoleResponseDTO> roles = new ArrayList<>();

        if (user.getRols()!= null){
            for (Role r: user.getRols()){
                roles.add(new RoleResponseDTO(r.getName(), r.getDescription()));
            }
        }
        return new UserRolesResponseDTO(userResponse, roles);
    }


    
}
