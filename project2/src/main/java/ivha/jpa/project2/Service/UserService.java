package ivha.jpa.project2.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.UserRequestDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.DTO.UserRolesResponseDTO;
import ivha.jpa.project2.Mapper.UserMapper;
import ivha.jpa.project2.Model.Customer;
import ivha.jpa.project2.Model.Role;
import ivha.jpa.project2.Model.User;
import ivha.jpa.project2.Repository.RoleRepository;
import ivha.jpa.project2.Repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository repo;
    private final RoleRepository roleRepo;

    public UserService(UserMapper userMapper, UserRepository repo, RoleRepository roleRepo){
        this.userMapper = userMapper;
        this.repo = repo;
        this.roleRepo = roleRepo;
    }

    // Crea un usuari + el customer vinculat
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequest){
        // Crea les entitats user i customer
        User user = userMapper.toUser(userRequest);
        Customer customer = userMapper.toCustomer(userRequest);
        // Afegeix el customer al user amb el setter especial i guarda a la BD
        user.setCustomer(customer);
        repo.save(user);
        return userMapper.toUserResponseDTO(user);
        
    }

    // Retorna el user i el customer de l'user amb l'id passat per path
    @Transactional
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

    // Esborra els rols passats al user indicat
    @Transactional
    public UserRolesResponseDTO deleteRoles(int id, List<Integer> roleIds) {

        // Busquem el user
        Optional<User> optUser = repo.findById(id);
        // Busquem els rols a esborrar
        List<Role> roles = roleRepo.findAllById(roleIds);

        if (!optUser.isPresent()) return null;

        // Esborrem els rols al user i guardem a la base de dades. S'esborren per cascade.
        User user = optUser.get();
        user.getRols().removeAll(roles);
        User user2 = repo.save(user);
        return userMapper.toUserRolesResponseDTO(user2);

    }



}
