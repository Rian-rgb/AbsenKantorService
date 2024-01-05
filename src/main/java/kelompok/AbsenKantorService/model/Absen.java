package kelompok.AbsenKantorService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "absen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Absen {

    @Id
    @Column(name = "absen_id",length = 36, unique = true)
    private String absenId = UUID.randomUUID().toString();

    @Column(name = "m_user_id", length = 36, columnDefinition = "VARCHAR(36) REFERENCES m_user(m_user_id)")
    private String mUserId;

    @Column(name = "tipe", length = 30)
    private String tipe;

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
}
