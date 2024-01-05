package kelompok.AbsenKantorService.pojo;

import kelompok.AbsenKantorService.model.User;
import lombok.Data;

@Data
public class AuthInPojo {

    private User user;
    private String token;
}
