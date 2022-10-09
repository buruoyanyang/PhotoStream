package photo.stream.model.vo;

import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xuhf
 * @date 2022/10/9
 */
@Data
public class FileInfoVo extends BaseVo{
    @Serial
    private static final long serialVersionUID = 7438559604680296847L;

    private List<Long> accessUserIdList;

    private Long fileSize;

    private String filenameOnServer;

    private String originalFilename;

    private LocalDateTime firstUploadTime;
}
