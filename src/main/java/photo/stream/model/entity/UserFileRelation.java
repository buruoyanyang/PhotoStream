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
@Entity(name = UserFileRelation.JPA_NAME)
@Table(name = UserFileRelation.TABLE_NAME)
public class UserFileRelation extends BaseEntity{
    @Serial
    private static final long serialVersionUID = 4215390562866827778L;

    public static final String TABLE_NAME = "user_file_relation";
    public static final String JPA_NAME = "userFileRelation";

    private String originName;

    private LocalDateTime uploadTime;

    private Long userId;

    private Long fileInfoId;

}
