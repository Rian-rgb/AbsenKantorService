package kelompok.AbsenKantorService.controller;

import kelompok.AbsenKantorService.model.User;
import kelompok.AbsenKantorService.pojo.UserPojo;
import kelompok.AbsenKantorService.repository.UserRepository;
import kelompok.AbsenKantorService.utils.CapitalWord;
import kelompok.AbsenKantorService.utils.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CapitalWord capitalWord;

    @PostMapping("/add")
    public ResponseEntity<MessageModel> insertData(@RequestBody UserPojo param) {
        MessageModel msg = new MessageModel();
        try {

            // Validasi
            User userCek = userRepository.getByNip(param.getNip());

            if (userCek != null){
                msg.setStatus(false);
                msg.setMessage("Nip Ini Sudah Terdaftar!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }

            User user = new User();

            user.setNip(param.getNip());
            user.setNama(param.getNama());
            user.setEmail(param.getEmail());
            user.setNoTlp(param.getNoTlp());
            user.setPassword(param.getPassword());
            user.setCreatedBy(user.getMUserId());
            user.setUpdatedBy(user.getMUserId());
            user.setRole(param.getRole());

            String jabatan = capitalWord.capitalizeWords(param.getJabatan());
            user.setJabatan(jabatan);

            User result = userRepository.save(user);

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

    @PutMapping("/edit")
    public ResponseEntity<MessageModel> updateData(@RequestBody UserPojo param){
        MessageModel msg = new MessageModel();
        try{

            User user = userRepository.getById(param.getMUserId());

            if (user == null){

                msg.setStatus(false);
                msg.setMessage("ID User Tidak Ditemukan!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }

            user.setNama(param.getNama());
            user.setEmail(param.getEmail());
            user.setNoTlp(param.getNoTlp());
            user.setUpdatedBy(param.getUpdatedBy());
            user.setUpdated(new Date());
            user.setPassword(param.getPassword());
            user.setRole(param.getRole());

            String jabatan = capitalWord.capitalizeWords(param.getJabatan());
            user.setJabatan(jabatan);

            User result = userRepository.save(user);

            msg.setStatus(true);
            msg.setMessage("SUCCESS");
            msg.setData(user);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    @GetMapping("/getByOne/{mUserId}")
    public ResponseEntity<MessageModel> getById(@PathVariable("mUserId") String mUserId) {
        MessageModel msg = new MessageModel();
        try {
            User data = userRepository.getById(mUserId);

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

    @GetMapping("/getAll")
    public ResponseEntity<MessageModel> getUserAll() {
        MessageModel msg = new MessageModel();
        try {
            List<User> data = (List<User>) userRepository.findAll();

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
