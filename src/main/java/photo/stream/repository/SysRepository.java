package photo.stream.repository;

import org.springframework.stereotype.Repository;
import photo.stream.model.entity.SysUser;


/**
 * @author xuhf
 * @date 2022/10/10
 */
@Repository
public interface SysRepository extends BaseRepository<SysUser, Long>{
}
