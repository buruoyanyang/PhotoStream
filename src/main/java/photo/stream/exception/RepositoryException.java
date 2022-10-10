package photo.stream.exception;

import photo.stream.enums.ErrorEnums;

import java.io.Serial;

/**
 * @author xuhf
 * @date 2022/10/10
 */
public class RepositoryException extends BaseException{

    @Serial
    private static final long serialVersionUID = -96772283720778242L;

    public RepositoryException(int code, String msg) {
        super(code, msg);
    }

    public RepositoryException(ErrorEnums error) {
        super(error);
    }
}
