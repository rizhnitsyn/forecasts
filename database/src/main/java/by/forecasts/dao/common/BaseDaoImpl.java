package by.forecasts.dao.common;

import by.forecasts.entities.BaseEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    private BaseDaoImpl() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public Long save(T entityToSave) {
        sessionFactory.getCurrentSession().save(entityToSave);
        return entityToSave.getId();
    }

    @Override
    public T findById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select e from " + entityClass.getName() + " e ", entityClass).getResultList();
    }

    @Override
    public void delete(T deleteEntity) {
        sessionFactory.getCurrentSession().delete(deleteEntity);
    }

    @Override
    public void update(T updatedEntity) {
        sessionFactory.getCurrentSession().update(updatedEntity);
    }
}
