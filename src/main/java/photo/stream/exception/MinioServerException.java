package photo.stream.exception;

import photo.stream.enums.ErrorEnums;

import java.io.Serial;

/**
 * @author xuhf
 * @date 2022/10/9
 */
public class MinioServerException extends BaseException{
    @Serial
    private static final long serialVersionUID = -5250727694670390007L;

    public MinioServerException() {
        super(ErrorEnums.FILE_SERVER_ERROR);
    }
}
