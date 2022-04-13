package org.util;

import org.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.List;

public class DAO {
    private final SessionFactory factory;
    private Session session;


    public DAO() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void close() {
        factory.close();
    }

    public List<Task> getTaskList() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Task> taskList = session.createQuery("from Task ORDER BY priority DESC").getResultList();
            session.getTransaction().commit();
            return taskList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Task> getTaskListWithPagination(int page) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM Task");
            query.setFirstResult((page - 1) * 10);
            query.setMaxResults(10);
            List<Task> taskList = query.list();
            session.getTransaction().commit();
            return taskList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCountPageTask() {
        try (Session session = factory.getCurrentSession()) {
            //Total
            session.beginTransaction();
            String countQ = "Select count (t.id) from Task t";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();
            int pageSize = 10;
            int lastPageNumber = (int) ((countResults / pageSize) + 1);
            session.getTransaction().commit();

            return lastPageNumber;
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
