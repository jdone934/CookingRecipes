package edu.matc.persistence;

import edu.matc.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsersDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get user by id
     */
    public Users getById(int id) {
        Session session = sessionFactory.openSession();
        Users user = session.get( Users.class, id );
        session.close();
        return user;
    }

    /**
     * update user
     * @param user  User to be inserted or updated
     */
    public void saveOrUpdate(Users user) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        session.close();
    }

    /**
     * update user
     * @param user  User to be inserted or updated
     */
    public int insert(Users user) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a user
     * @param user User to be deleted
     */
    public void delete(Users user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }


    /** Return a list of all users
     *
     * @return All users
     */
    public List<Users> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> query = builder.createQuery( Users.class );
        Root<Users> root = query.from( Users.class );
        List<Users> users = session.createQuery( query ).getResultList();

        logger.debug("The list of users " + users);
        session.close();

        return users;
    }

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Users> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> query = builder.createQuery( Users.class );
        Root<Users> root = query.from( Users.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Users> users = session.createQuery( query ).getResultList();

        session.close();
        return users;
    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Users> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> query = builder.createQuery( Users.class );
        Root<Users> root = query.from( Users.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Users> users = session.createQuery( query ).getResultList();
        session.close();
        return users;
    }

}
