package kelompok.AbsenKantorService.controller;

import kelompok.AbsenKantorService.model.User;
import kelompok.AbsenKantorService.pojo.AuthInPojo;
import kelompok.AbsenKantorService.pojo.AuthPojo;
import kelompok.AbsenKantorService.pojo.UserPojo;
import kelompok.AbsenKantorService.repository.UserRepository;
import kelompok.AbsenKantorService.utils.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<MessageModel> loginUser(@RequestBody AuthPojo param) {
        MessageModel msg = new MessageModel();
        try {

            User user = userRepository.getByNipPassRole(param.getNip(), param.getPassword(), param.getRole());

            if (user == null){

                msg.setStatus(false);
                msg.setData(null);
                msg.setMessage("Nip, Password dan Role Salah");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            } else {

                String genarateToken = generateNewToken();
                AuthInPojo authIn = new AuthInPojo();

                authIn.setUser(user);
                authIn.setToken(genarateToken);

                msg.setStatus(true);
                msg.setData(authIn);
                msg.setMessage("SUCCESS");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
