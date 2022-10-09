package photo.stream.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xuhf
 * @date 2022/10/9
 */
@AllArgsConstructor
public enum ErrorEnums {

    /**
     * 未知错误，没有被捕获的异常都会抛出此错误
     */
    UNKNOWN_ERROR(-1, "未知错误"),

    /**
     * minio服务器异常
     */
    FILE_SERVER_ERROR(-10001, "文件服务异常"),

    /**
     * MultipartFile 读取失败
     */
    FILE_STREAM_ERROR(-10002, "文件流异常");


    @Getter
    private final int code;

    @Getter
    private final String msg;
}
