package photo.stream.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@Data
@Entity(name = SysUser.JPA_NAME)
@Table(name = SysUser.TABLE_NAME)
public class SysUser extends BaseEntity{
    @Serial
    private static final long serialVersionUID = -6776428730208934227L;

    public static final String TABLE_NAME = "sys_user";
    public static final String JPA_NAME = "sysUser";

    private String username;

    private String staffName;

    private Integer status;

    private String password;
}
