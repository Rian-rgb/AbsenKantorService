package kelompok.AbsenKantorService.repository;

import kelompok.AbsenKantorService.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

    @Query("SELECT e FROM Role e WHERE nama =:nama")
    Role getByNama(@Param("nama")String nama);
}
