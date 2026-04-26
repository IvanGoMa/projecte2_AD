package ivha.jpa.project2.Service;

import java.util.ArrayList;
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

    //Modifica la informació de l'usuari/customer amb l'id passat per path
    @Transactional
    public UserResponseDTO updateUser(int id, UserRequestDTO userRequest) {
        // Busquem el user
        Optional<User> optUser = repo.findById(id); // Optional per evitar null pointer exception en cas de que no existeixi el user amb l'id passat per path
        if (!optUser.isPresent()) return null;

        // Usuari existent
        User user = optUser.get();
        
        // verifiquem les dades modificades de l'usuari
        if (userRequest.getEmail() != null){ user.setEmail(userRequest.getEmail());}
        if (userRequest.getPassword() != null){user.setPassword(userRequest.getPassword());}
        if (userRequest.getStatus() != null){user.setStatus(userRequest.getStatus());}

        //Verifiquem si hi han dades modificades del customer relacionat al usuari
        Customer customer = user.getCustomer();
        if(userRequest.getFirstName() != null){customer.setFirstName(userRequest.getFirstName());}
        if(userRequest.getLastName() != null){customer.setLastName(userRequest.getLastName());}
        if(userRequest.getPhone() != null){customer.setPhone(userRequest.getPhone());}

        // Guardem a la base de dades
        User user2 = repo.save(user);
        return userMapper.toUserAndCustomerResponseDTO(user2);
    }

    //Retorna tots els usuaris amb el deu id, email i el customer relacionat
    @Transactional
    public List<UserResponseDTO> getUsers(){
        List<User> users = repo.findAll();
        
        ArrayList<UserResponseDTO> responseDTOs = new ArrayList<>(userMapper.toUsersAndCustomersResponseDTO(users));

        return responseDTOs;
    }

    // Afegeix rols a un usuari (x id) i retorna la info de l'usuari i els rols relacionats
    @Transactional
    public UserRolesResponseDTO addRoles(int id, List<Integer> roleIds) {

        // Busquem el user
        Optional<User> optUser = repo.findById(id);
        if (!optUser.isPresent()) return null;
        // Busquem els rols a afegir
        List<Role> roles = roleRepo.findAllById(roleIds);//ignora si li mandem un rol que no existeix, sense donar error

        User user = optUser.get();

        //Comprovem que no s'estigui afegint un rol que ja té l'usuari, si es així retornem error
        for (Role r: roles){
            if (!user.getRols().contains(r)){
                user.getRols().add(r); // nomes afegir si es un rol que l'usuari no tenia previament
            }
        }

        User user2 = repo.save(user);
        return userMapper.toUserRolesResponseDTO(user2);
    }


}
