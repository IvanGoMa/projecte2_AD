package ivha.jpa.project2.Service;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.UserRequestDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.Mapper.UserMapper;
import ivha.jpa.project2.Model.Customer;
import ivha.jpa.project2.Model.User;
import ivha.jpa.project2.Repository.UserRepository;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository repo;

    public UserService(UserMapper userMapper, UserRepository repo){
        this.userMapper = userMapper;
        this.repo = repo;
    }


    public UserResponseDTO createUser(UserRequestDTO userRequest){

        User user = userMapper.toUser(userRequest);
        Customer customer = userMapper.toCustomer(userRequest);
        user.setCustomer(customer);
        repo.save(user);
        return userMapper.toUserResponseDTO(user);
        
    }


    public UserResponseDTO getUser(Long id) {
        try{
                User u = repo.findByIdAndStatusTrue(id);
                if(u != null){
                    return userMapper.toUserAndCustomerResponseDTO(u);
                } else {
                    return null;
                }
            } catch (Exception e){
                return null;
            }
    }



}
