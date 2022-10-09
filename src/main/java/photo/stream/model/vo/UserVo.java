package photo.stream.model.vo;

import lombok.Data;

import java.io.Serial;

/**
 * @author xuhf
 * @date 2022/10/9
 */

@Data
public class UserVo extends BaseVo{
    @Serial
    private static final long serialVersionUID = 3671124590233145914L;

    private Long userId;

    private String username;

    private String staffName;

    private Integer status;

}
