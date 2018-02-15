package by.forecasts.dao.common;

import by.forecasts.entities.BaseEntity;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

import java.util.List;

public abstract class BaseDaoImpl<T extends BaseEntity> {

    private Class<T> entityClass;

    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Long save(T entityToSave) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        session.save(entityToSave);

        session.getTransaction().commit();
        session.close();
        return entityToSave.getId();
    }

    public T findById(Long id) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        T foundEntity = session.get(entityClass, id);

        session.getTransaction().commit();
        session.close();
        return foundEntity;
    }

    public List<T> findAll() {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        List<T> resultList = session.createQuery("select e from " + entityClass.getName() + " e ", entityClass).getResultList();

        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    public void delete(T deleteEntity) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        session.delete(deleteEntity);

        session.getTransaction().commit();
        session.close();
    }

    public void update(T updatedEntity) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        session.update(updatedEntity);

        session.getTransaction().commit();
        session.close();
    }
}
