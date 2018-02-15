package by.forecasts.dao.common;

import by.forecasts.entities.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    Long save(T entity);

    T findById(Long id);

    List<T> findAll();

    void delete(T deleteEntity);

    void update(T updatedEntity);
}
