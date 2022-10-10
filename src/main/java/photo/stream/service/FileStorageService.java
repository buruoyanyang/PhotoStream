package photo.stream.service;

import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import photo.stream.config.MinioConfig;
import photo.stream.exception.FileUploadException;
import photo.stream.exception.MinioServerException;
import photo.stream.utils.MD5Util;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuhf
 * @date 2022/10/8
 */
@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final MinioConfig minioConfig;

    private final UserService userService;

    private MinioClient minioClient;

    @SneakyThrows
    private synchronized MinioClient getClient() {
        if (null != minioClient) {
            return this.minioClient;
        }
        this.minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfig.getBucket()).build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfig.getBucket()).build());
        }
        return minioClient;
    }

    private boolean fileExist(MultipartFile file) {
        String fileMd5 = MD5Util.getInstance().get(file);
        return fileExist(fileMd5);
    }

    public String getFilename(MultipartFile file) {
        return getFilename(MD5Util.getInstance().get(file));
    }

    private String getFilename(String md5) {
        return String.format(minioConfig.getFilenamePre(), md5);
    }

    public boolean fileExist(String fileMd5) {
        String filename = this.getFilename(fileMd5);
        try {
            this.getClient().statObject(StatObjectArgs.builder().bucket(minioConfig.getBucket()).object(filename).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String upload(MultipartFile file) {
        try (InputStream is = file.getInputStream()){
            String fileMd5 = MD5Util.getInstance().get(file);
            String filename = this.getFilename(fileMd5);
            // 判定文件是否存在
            if (fileExist(fileMd5)) {
                return filename;
            }
            this.getClient()
                    .putObject(PutObjectArgs.builder()
                            .object(filename)
                            .bucket(minioConfig.getBucket())
                            .stream(is, is.available(), -1)
                            .contentType(file.getContentType())
                            .build());
            return filename;
        } catch (IOException e) {
            // 读取文件异常
            throw new FileUploadException();
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            throw new MinioServerException();
        }
    }

}
