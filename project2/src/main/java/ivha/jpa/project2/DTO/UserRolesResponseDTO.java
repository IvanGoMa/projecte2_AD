package ivha.jpa.project2.DTO;

import java.util.List;

public class UserRolesResponseDTO {

    private UserResponseDTO user;
    private List<RoleResponseDTO> roles;
    
    public UserRolesResponseDTO(UserResponseDTO user, List<RoleResponseDTO> roles) {
        this.user = user;
        this.roles = roles;
    }
    
    public UserResponseDTO getUser() {
        return user;
    }
    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
    public List<RoleResponseDTO> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleResponseDTO> roles) {
        this.roles = roles;
    }

    
}
