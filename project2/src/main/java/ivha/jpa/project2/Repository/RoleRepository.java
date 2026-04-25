package ivha.jpa.project2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ivha.jpa.project2.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>{

}
