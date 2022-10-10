package photo.stream.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@Data
@Entity(name = FileInfo.JPA_NAME)
@Table(name = FileInfo.TABLE_NAME)
public class FileInfo extends BaseEntity{
    @Serial
    private static final long serialVersionUID = -2258398597692716526L;

    public static final String TABLE_NAME = "file_info";
    public static final String JPA_NAME = "fileInfo";

    /**
     * 在存储中的文件名
     */
    private String storageFilename;

    private Long firstUserId;

    private Long fileSize;

    /**
     * 下载地址过期时间
     */
    private LocalDateTime downloadPathExpTime;

    /**
     * 已经生成的下载地址
     */
    private String lastDownloadPath;

    private Integer status;

}
