package kelompok.AbsenKantorService.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "m_user")
public class User {

    @Id
    @Column(name = "m_user_id",length = 36, unique = true)
    private String mUserId = UUID.randomUUID().toString();

    @Column(name = "nip", length = 30, unique = true)
    private String nip;

    @Column(name = "nama", length = 255)
    private String nama;

    @Column(name = "no_tlp", length = 20)
    private String noTlp;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "created",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Column(name = "createdby",length = 255)
    private String createdBy;

    @Column(name = "updated",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated = new Date();

    @Column(name = "updatedby",length = 255)
    private String updatedBy;

    @Column(name = "isactive",length = 1, columnDefinition = "varchar(1) default 'Y'")
    private String isactive = "Y";

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "role", length = 255, columnDefinition = "varchar(255) REFERENCES m_role(nama)")
    private String role;

    @Column(name = "jabatan", length = 255, columnDefinition = "varchar(255) REFERENCES m_jabatan(nama)")
    private String jabatan;

}
