package kelompok.AbsenKantorService.model;

import jdk.jfr.Enabled;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Immutable
@Subselect("select a.*, m.nama as nama_pegawai from absen a, m_user m WHERE a.m_user_id = m.m_user_id")
public class AbsenView {

    @Id
    private String absenId;

    private String mUserId;

    private String tipe;

    private Date created;

    private String createdby;

    private Date updated;

    private String updatedby;

    private String isactive;

    private String namaPegawai;
}
