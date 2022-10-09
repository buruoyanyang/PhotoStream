package photo.stream.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuhf
 * @date 2022/9/29
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endpoint;

    private String filenamePre;

    private String bucket;

    private String accessKey;

    private String secretKey;
}
