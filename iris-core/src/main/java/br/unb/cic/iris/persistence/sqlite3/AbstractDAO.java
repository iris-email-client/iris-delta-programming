package br.unb.cic.iris.persistence.sqlite3;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;

import br.unb.cic.iris.core.exception.DBException;
import br.unb.cic.iris.util.HibernateUtil;

public abstract class AbstractDAO<T> {
	private Class<T> clazz;
	protected Session session;
	
	public AbstractDAO(){
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println("******************************** CLAZZ="+clazz);
	}
	
	public void saveOrUpdate(T obj) throws DBException {
        try {
            startSession();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	closeSession();
        }
    }
	
	public void delete(T t) throws DBException {
		try {
            startSession();
            session.delete(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	closeSession();
        }
	}
	
	public T findById(Long id) throws DBException {
        T obj = null;
        try {
            startSession();
            obj = (T) session.load(clazz, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	closeSession();
        }
        return obj;
    }

    public List<T> findAll() throws DBException {
        List<T> objects = null;
        try {
            startSession();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	closeSession();
        }
        return objects;
    }

	public List<T> findByExample(T filtro, MatchMode matchMode, boolean ignoreCase){
		org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(filtro);

		if(matchMode != null){
			example = example.enableLike(matchMode);
		}

		if(ignoreCase){
			example = example.ignoreCase();
		}

		return session.createCriteria(clazz).add(example).list();
	}
	
	protected void handleException(Exception e) throws DBException {
		session.getTransaction().rollback();
        throw new DBException(e.getMessage(), e);
    }

    protected void startSession() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }
    
	protected void closeSession() {
		if(session != null && session.isOpen()) {
			session.flush();
			session.close();
		}
	}
}
