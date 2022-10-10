package photo.stream.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5372405809140206256L;

    public static final String TABLE_NAME = null;
    public static final String JPA_NAME = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean valid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
