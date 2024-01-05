package kelompok.AbsenKantorService.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class JabatanPojo {

    private String nama;

    private String createdBy;

    private String updatedBy;

    private String isactive;
}
