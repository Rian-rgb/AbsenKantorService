package kelompok.AbsenKantorService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "m_jabatan")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Jabatan {

    @Id
    @Column(name = "nama", length = 255, unique = true)
    private String nama;

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
