package kelompok.AbsenKantorService.controller;

import kelompok.AbsenKantorService.model.Absen;
import kelompok.AbsenKantorService.model.AbsenView;
import kelompok.AbsenKantorService.model.User;
import kelompok.AbsenKantorService.pojo.AbsenPojo;
import kelompok.AbsenKantorService.pojo.UserPojo;
import kelompok.AbsenKantorService.repository.AbsenRepository;
import kelompok.AbsenKantorService.repository.UserRepository;
import kelompok.AbsenKantorService.utils.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absen")
public class AbsenController {

    @Autowired
    private AbsenRepository absenRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<MessageModel> insertData(@RequestBody AbsenPojo param) {
        MessageModel msg = new MessageModel();
        try {

            User user = userRepository.getById(param.getMUserId());

            if (user == null){

                msg.setStatus(false);
                msg.setMessage("mUserId Tidak Ditemukan");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }


            if (param.getTipe().toUpperCase().equals("MASUK")) {
                Absen absenMasuk = absenRepository.getAbsenMasukNow(param.getMUserId());

                if (absenMasuk != null){

                    msg.setStatus(false);
                    msg.setMessage("Kamu Sudah Melakukan Absen Masuk!");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
                }
            } else if (param.getTipe().toUpperCase().equals("KELUAR")) {

                Absen absenMasuk = absenRepository.getAbsenMasukNow(param.getMUserId());

                if (absenMasuk == null){

                    msg.setStatus(false);
                    msg.setMessage("Absen Masuk Telebih Dahulu!");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
                }

                Absen absenKeluar = absenRepository.getAbsenKeluarNow(param.getMUserId());

                if (absenKeluar != null){

                    msg.setStatus(false);
                    msg.setMessage("Kamu Sudah Melakukan Absen Keluar!");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
                }


            } else {
                msg.setStatus(false);
                msg.setMessage("Tipe Hanya 'MASUK' Atau 'KELUAR'!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }


            Absen absen = new Absen();

            absen.setMUserId(param.getMUserId());
            absen.setTipe(param.getTipe().toUpperCase());
            absen.setCreatedBy(user.getMUserId());
            absen.setUpdatedBy(user.getMUserId());

            Absen result = absenRepository.save(absen);

            msg.setStatus(true);
            msg.setData(result);
            msg.setMessage("SUCCESS");
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    @GetMapping("/getList")
    public ResponseEntity<MessageModel> getAbsenList(@RequestParam(value = "mUserId", required = false)String mUserId) {
        MessageModel msg = new MessageModel();
        try {
            List<AbsenView> result = absenRepository.getBymUserId(mUserId);

            msg.setStatus(true);
            msg.setMessage("Success");
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    @GetMapping("/cekStatus")
    public ResponseEntity<MessageModel> getStatusAbsen(@RequestParam("tipe")String tipe,
                                                       @RequestParam("mUserId")String mUserId) {
        MessageModel msg = new MessageModel();
        try {

            String statusAbsen = null;
            if (tipe.toUpperCase().equals("MASUK")) {
                Absen absenMasuk = absenRepository.getAbsenMasukNow(mUserId);

                if (absenMasuk != null){

                    statusAbsen = "SUDAH";
                } else {

                    statusAbsen = "BELUM";
                }
            } else if (tipe.toUpperCase().equals("KELUAR")) {

                Absen absenKeluar = absenRepository.getAbsenKeluarNow(mUserId);

                if (absenKeluar != null){

                    statusAbsen = "SUDAH";
                } else {

                    statusAbsen = "BELUM";
                }


            } else {
                msg.setStatus(false);
                msg.setMessage("Tipe Hanya 'MASUK' Atau 'KELUAR'!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }


            msg.setStatus(true);
            msg.setMessage(statusAbsen);
            msg.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }
}
