package by.forecasts.dao;

import by.forecasts.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class BaseDao<T extends BaseEntity> {
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Long save(T entityToSave) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.save(entityToSave);

        session.getTransaction().commit();
        session.close();
        return entityToSave.getId();
    }

    public T findById(Long id) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        T foundEntity = session.get(entityClass, id);

        session.getTransaction().commit();
        session.close();
        return foundEntity;
    }

    public List<T> findAll() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<T> resultList = session.createQuery("select e from " + entityClass.getName() + " e ", entityClass).getResultList();

        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    public void delete(T deleteEntity) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.delete(deleteEntity);

        session.getTransaction().commit();
        session.close();
    }

    public void update(T updatedEntity) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.update(updatedEntity);

        session.getTransaction().commit();
        session.close();
    }


}
