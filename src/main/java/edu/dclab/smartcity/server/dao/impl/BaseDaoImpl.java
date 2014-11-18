package edu.dclab.smartcity.server.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import edu.dclab.smartcity.server.dao.IBaseDao;

@Repository("baseDao")
public class BaseDaoImpl<T extends Serializable> implements IBaseDao<T> {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sf;
	
	public BaseDaoImpl() {
	}
	
    public SessionFactory getSessionFactory() {
        return sf;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }
	
	public Session getSession() {
		return sf.getCurrentSession();
	}
	
	public void save(T t) {
		getSession().save(t);
	}
	
	public void update(T t) {
		getSession().update(t);
		//getSession().flush();
	}
	
	public void deleteById(Class<T> clazz, long id) {
        getSession().delete(queryById(clazz, id));
    }

    public void delete(T t) {
        getSession().delete(t);
    }
    
    public long rowCount(Class<T> clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        List list = (List<T>) criteria.list();
        long count = (Long)list.get(0);
        return count;
    }
    
    @SuppressWarnings("unchecked")
    public T queryById(Class<T> clazz, long id) {
        return (T) getSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(Class<T> clazz, String property, String value) {
        return (List<T>) getSession().createCriteria(clazz)
                .add(eq(property, value)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryAll(Class<T> clazz) {
        return (List<T>) getSession().createCriteria(clazz).list();
    }

    @SuppressWarnings("unchecked")
    public T queryObjectBySql(Class<T> clazz, String sql) {
        T object = (T) getSession().createSQLQuery(sql).addEntity(clazz)
                .uniqueResult();

        return object;
    }

    @SuppressWarnings("unchecked")
    public List<T> queryAllBySql(Class<T> clazz, String sql) {
        return (List<T>) getSession().createSQLQuery(sql).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByPage(int pageNum, int pageSize, Class<T> clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult( (pageNum-1) * pageSize);
        criteria.setMaxResults(pageSize);
        
        return (List<T>)criteria.list();
    }
}
