package photo.stream.exception;

import photo.stream.enums.ErrorEnums;

import java.io.Serial;

/**
 * @author xuhf
 * @date 2022/10/9
 */
public class FileUploadException extends BaseException{

    @Serial
    private static final long serialVersionUID = -6877709279443973295L;

    public FileUploadException() {
        super(ErrorEnums.FILE_STREAM_ERROR);
    }
}
