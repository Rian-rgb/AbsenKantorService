package kelompok.AbsenKantorService.controller;

import kelompok.AbsenKantorService.model.Jabatan;
import kelompok.AbsenKantorService.pojo.JabatanPojo;
import kelompok.AbsenKantorService.repository.JabatanRepository;
import kelompok.AbsenKantorService.utils.CapitalWord;
import kelompok.AbsenKantorService.utils.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jabatan")
public class JabatanController {

    @Autowired
    private JabatanRepository jabatanRepository;

    @Autowired
    private CapitalWord capitalWord;

    @PostMapping("/add")
    public ResponseEntity<MessageModel> add(@RequestBody JabatanPojo param) {
        MessageModel msg = new MessageModel();
        try {

            String nama = capitalWord.capitalizeWords(param.getNama());
            Jabatan namaJabatanCheck = jabatanRepository.getByNama(nama);

            if (namaJabatanCheck != null){

                msg.setStatus(false);
                msg.setMessage("Nama Jabatan Sudah Dibuat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }

            Jabatan jabatan = new Jabatan();

            jabatan.setNama(nama);
            jabatan.setCreatedBy(param.getCreatedBy());
            jabatan.setUpdatedBy(param.getUpdatedBy());

            Jabatan result = jabatanRepository.save(jabatan);

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

    @GetMapping("/getAll")
    public ResponseEntity<MessageModel> getRoleAll() {
        MessageModel msg = new MessageModel();
        try {
            List<Jabatan> data = (List<Jabatan>) jabatanRepository.findAll();

            msg.setStatus(true);
            msg.setMessage("Success");
            msg.setData(data);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }
}
