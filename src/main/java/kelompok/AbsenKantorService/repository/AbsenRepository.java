package kelompok.AbsenKantorService.repository;

import kelompok.AbsenKantorService.model.Absen;
import kelompok.AbsenKantorService.model.AbsenView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenRepository extends PagingAndSortingRepository<Absen, String> {

    @Query("SELECT E FROM Absen E WHERE absenId =:absenId")
    Absen getById(String absenId);

    @Query("SELECT E FROM Absen E WHERE tipe = 'MASUK' AND DATE(created) = current_date AND " +
            "mUserId =:mUserId")
    Absen getAbsenMasukNow(@Param("mUserId")String mUserId);

    @Query("SELECT E FROM Absen E WHERE tipe = 'KELUAR' AND DATE(created) = current_date AND " +
            "mUserId =:mUserId")
    Absen getAbsenKeluarNow(@Param("mUserId")String mUserId);

    @Query("SELECT E FROM AbsenView E WHERE mUserId =:mUserId OR :mUserId IS NULL")
    List<AbsenView> getBymUserId(String mUserId);
}
