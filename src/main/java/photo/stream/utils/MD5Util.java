package photo.stream.utils;

import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuhf
 * @date 2022/10/9
 */
public enum MD5Util {
    /**
     * INSTANCE
     */
    INSTANCE;

    MD5Util() {}

    public static MD5Util getInstance() {
        return INSTANCE;
    }

    public String get(MultipartFile file) {
        try {
            return DigestUtils.md5DigestAsHex(file.getInputStream());
        } catch (Exception ex) {
            return "";
        }
    }

}
