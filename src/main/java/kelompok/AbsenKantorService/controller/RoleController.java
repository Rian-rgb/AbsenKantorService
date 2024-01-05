package kelompok.AbsenKantorService.controller;

import kelompok.AbsenKantorService.model.Role;
import kelompok.AbsenKantorService.model.User;
import kelompok.AbsenKantorService.pojo.AuthInPojo;
import kelompok.AbsenKantorService.pojo.AuthPojo;
import kelompok.AbsenKantorService.pojo.RolePojo;
import kelompok.AbsenKantorService.repository.RoleRepository;
import kelompok.AbsenKantorService.repository.UserRepository;
import kelompok.AbsenKantorService.utils.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/add")
    public ResponseEntity<MessageModel> add(@RequestBody RolePojo param) {
        MessageModel msg = new MessageModel();
        try {

            Role namaRoleCheck = roleRepository.getByNama(param.getNama());

            if (namaRoleCheck != null){

                msg.setStatus(false);
                msg.setMessage("Nama Role Sudah Dibuat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }
            Role role = new Role();

            role.setNama(param.getNama());
            role.setCreatedBy(param.getCreatedBy());
            role.setUpdatedBy(param.getUpdatedBy());

            Role result = roleRepository.save(role);

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
            List<Role> data = (List<Role>) roleRepository.findAll();

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
