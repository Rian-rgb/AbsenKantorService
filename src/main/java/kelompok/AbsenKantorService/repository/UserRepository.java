package kelompok.AbsenKantorService.repository;

import kelompok.AbsenKantorService.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    @Query("SELECT E FROM User E WHERE mUserId =:mUserId")
    User getById(String mUserId);

    @Query("SELECT E FROM User E WHERE nip =:nip AND password =:password AND role =:role")
    User getByNipPassRole(String nip, String password, String role);

    @Query("SELECT E FROM User E WHERE nip =:nip")
    User getByNip(String nip);
}
