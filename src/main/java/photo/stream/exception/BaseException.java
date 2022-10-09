package photo.stream.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import photo.stream.enums.ErrorEnums;

import java.io.Serial;

/**
 * @author xuhf
 * @date 2022/10/9
 */
@Data
@AllArgsConstructor
public abstract class BaseException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 6386182161399275639L;

    private int code;

    private String msg;

    public BaseException(ErrorEnums error) {
        this.code = error.getCode();
        this.msg = error.getMsg();
    }
}
