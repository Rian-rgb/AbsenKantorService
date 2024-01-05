package kelompok.AbsenKantorService.repository;

import kelompok.AbsenKantorService.model.Jabatan;
import kelompok.AbsenKantorService.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JabatanRepository extends PagingAndSortingRepository<Jabatan, String> {

    @Query("SELECT e FROM Jabatan e WHERE nama =:nama")
    Jabatan getByNama(@Param("nama")String nama);
}
