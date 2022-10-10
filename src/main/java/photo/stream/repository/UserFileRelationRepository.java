package photo.stream.repository;

import org.springframework.stereotype.Repository;
import photo.stream.model.entity.UserFileRelation;


/**
 * @author xuhf
 * @date 2022/10/10
 */
@Repository
public interface UserFileRelationRepository extends BaseRepository<UserFileRelation, Long>{
}
