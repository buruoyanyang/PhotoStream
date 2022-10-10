package photo.stream.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.CollectionUtils;
import photo.stream.enums.ErrorEnums;
import photo.stream.exception.RepositoryException;
import photo.stream.model.entity.BaseEntity;
import photo.stream.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2022/10/10
 */
@NoRepositoryBean
@Slf4j
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public T create(T entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setCreateTime(LocalDateTime.now());
            if (baseEntity.getValid() == null) {
                baseEntity.setValid(true);
            }
        }
        try {
            entityManager.persist(entity);
            return entity;
        } catch (Exception ex) {
            log.error("创建对象失败,对象内容为", ex);
            throw new RepositoryException(ErrorEnums.REPOSITORY_CREATE_FAILED);
        }
    }

    @Override
    public T update(T entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setUpdateTime(LocalDateTime.now());
        }
        this.entityManager.merge(entity);
        return entity;
    }

    @Override
    public Sort getSort(List<String> columnList, String direction) {
        String[] strings;
        if (!CollectionUtils.isEmpty(columnList)) {
            strings = new String[columnList.size()];
            columnList.toArray(strings);
        } else {
            strings = new String[]{"id"};
        }
        return Sort.by(Sort.Direction.fromString(direction), strings);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<Map> execSqlQueryForMap(String sql) {
        Query query = this.entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map>) query.getResultList();
    }

    @Override
    public void execSql(String sql) {
        Query query = this.entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
}
