package photo.stream.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import photo.stream.model.entity.FileInfo;
import photo.stream.model.entity.UserFileRelation;
import photo.stream.model.vo.UserVo;
import photo.stream.repository.FileInfoRepository;
import photo.stream.repository.UserFileRelationRepository;

import java.time.LocalDateTime;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@Service
@RequiredArgsConstructor
public class FileService {
    private final FileStorageService fileStorageService;

    private final FileInfoRepository fileInfoRepository;

    private final UserFileRelationRepository userFileRelationRepository;

    private final UserService userService;

    public void upload(MultipartFile file) {
        UserVo currentUser = userService.getCurrentUser();
        UserFileRelation relation = new UserFileRelation();
        relation.setUploadTime(LocalDateTime.now());
        relation.setOriginName(file.getOriginalFilename());
        relation.setUserId(currentUser.getUserId());

        String filename = fileStorageService.getFilename(file);
        FileInfo fileInfo = fileInfoRepository.findByStorageFilenameAndValidTrue(filename);
        if (fileInfo == null || fileInfo.getId() == null) {
            filename = fileStorageService.upload(file);
            fileInfo = new FileInfo();
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFirstUserId(currentUser.getUserId());
            fileInfo.setStorageFilename(filename);
            fileInfoRepository.create(fileInfo);
        }
        Long fileInfoId = fileInfo.getId();
        relation.setFileInfoId(fileInfoId);
        userFileRelationRepository.create(relation);
    }
}
