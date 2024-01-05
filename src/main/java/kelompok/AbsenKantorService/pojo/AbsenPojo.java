package kelompok.AbsenKantorService.pojo;

import lombok.Data;

import java.util.UUID;

@Data
public class AbsenPojo {

    private String absenId;

    private String mUserId;

    private String tipe;

    private String createdBy;

    private String updatedBy;

    private String isactive;
}
