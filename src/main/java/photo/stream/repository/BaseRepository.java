package photo.stream.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 创建
     * @param entity entity
     * @return entity
     */
    T create(T entity);

    /**
     * 修改
     * @param entity entity
     * @return entity
     */
    T update(T entity);

    /**
     * 获取排序
     * @param columnList 排序字段列表
     * @param direction 排序方向 asc or desc
     * @return Sort
     */
    Sort getSort(List<String> columnList, String direction);

    /**
     * 执行sql并返回以map组织的结构
     * @param sql sql
     * @return result map
     */
    @SuppressWarnings("rawtypes")
    List<Map> execSqlQueryForMap(String sql);


    /**
     * 执行sql
     * @param sql sql
     */
    void execSql(String sql);

}
