package ivha.jpa.project2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ivha.jpa.project2.Model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

    public User findByIdAndStatusTrue(Long id);

}
