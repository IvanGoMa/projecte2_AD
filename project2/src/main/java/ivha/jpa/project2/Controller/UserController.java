package ivha.jpa.project2.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ivha.jpa.project2.DTO.ErrorDTO;
import ivha.jpa.project2.DTO.UserRequestDTO;
import ivha.jpa.project2.DTO.UserResponseDTO;
import ivha.jpa.project2.DTO.UserRolesResponseDTO;
import ivha.jpa.project2.Service.UserService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    //2a Crea un usuari 
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user){
        try{
            UserResponseDTO response = service.createUser(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

    //2b Retorna un usuari amb l'id passada per path
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        try{
            UserResponseDTO user = service.getUser(id);
            if (user != null){
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(),"No s'ha trobat cap usuari amb id " + id));
            
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

    // 5b Elimina els rols que es passen pel body a l'usuari amb l'id que es passa per path
    @PatchMapping("/users/{id}/roles/delete")
    public ResponseEntity<?> deleteRoles(@PathVariable int id, @RequestBody List<Integer> roles){
        try{
            UserRolesResponseDTO response = service.deleteRoles(id, roles);
            if (response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(),"No s'ha trobat cap usuari amb id " + id));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

    // 2c Modifica la informació de l'usuari/customer amb l'id passada per path
    @PatchMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserRequestDTO user){
        try{
            UserResponseDTO response = service.updateUser(id, user);
            if (response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "No s'ha trobat cap usuari amb id: " + id));
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    //2d Retorna l'id, email de tots usuaris i la info dels customers relacionats
    @GetMapping("/users")
    public ResponseEntity<?> getUsuaris(){
        try{
            List<UserResponseDTO> response = service.getUsers();
            if (response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "No s'ha trobat cap usuari"));
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    // 5a Afegir rols a un usuari per id d'usuari i ids de rol
    @PatchMapping("/users/{id}/roles/add")
    public ResponseEntity<?> addRoles(@PathVariable int id, @RequestBody List<Integer> roles){
        try{
            UserRolesResponseDTO response = service.addRoles(id, roles);
            if (response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(),"No s'ha trobat cap usuari amb id " + id));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }
    

}
