package photo.stream.repository;

import org.springframework.stereotype.Repository;
import photo.stream.model.entity.FileInfo;
import photo.stream.model.entity.SysUser;


/**
 * @author xuhf
 * @date 2022/10/10
 */
@Repository
public interface FileInfoRepository extends BaseRepository<FileInfo, Long>{

    /**
     * 根据filename获取文件信息
     * @param filename filename
     * @return file info
     */
    FileInfo findByStorageFilenameAndValidTrue(String filename);
}
