package kelompok.AbsenKantorService.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserPojo {

    private String mUserId;

    private String nama;

    private String nip;

    private String noTlp;

    private String email;

    private String createdBy;

    private String updatedBy;

    private String password;

    private String role;

    private String jabatan;
}
