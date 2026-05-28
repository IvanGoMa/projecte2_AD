package ivha.jpa.project2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ivha.jpa.project2.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository <Address, Integer> {

    public void deleteAllByCustomerId(Integer id);

}
