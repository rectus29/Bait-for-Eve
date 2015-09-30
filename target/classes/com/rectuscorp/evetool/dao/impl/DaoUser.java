package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoUser;
import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.enums.State;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

@Repository("daoUser")
public class DaoUser extends GenericDaoHibernate<User, Long> implements IdaoUser {


    public DaoUser() {
        super(User.class);
    }

    public User getUserByUsername(String username) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("username", username));
        detachedCriteria.add(Restrictions.eq("state", State.ENABLE));
        List result = getHibernateTemplate().findByCriteria(detachedCriteria);
        if (result.size() == 0)
            return null;
        return (User) result.get(0);
    }


    public User getUserByName(String username) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("firstName", username));
        detachedCriteria.add(Restrictions.eq("state", State.ENABLE));
        List result = getHibernateTemplate().findByCriteria(detachedCriteria);
        if (result.size() == 0)
            return null;
        return (User) result.get(0);
    }

    public List getUsers(int first, int count, String sortProperty, boolean sortAsc, String filterName) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("state", State.ENABLE));
        if (sortAsc)
            detachedCriteria.addOrder(Order.asc(sortProperty));
        else
            detachedCriteria.addOrder(Order.desc(sortProperty));
        if (filterName != null && filterName.length() > 0)
            detachedCriteria.add(Restrictions.like("firstName", "%" + filterName + "%"));
        List result = getHibernateTemplate().findByCriteria(detachedCriteria, first, count);
        return result;
    }

    public List<User> getAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.addOrder(Order.asc("firstName"));
        //detachedCriteria.add(Restrictions.eq("enable", true));
        List result = getHibernateTemplate().findByCriteria(detachedCriteria);
        return result;
    }

    public List<User> getUsersByFilter(String filterName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.addOrder(Order.asc("firstName"));
        detachedCriteria.add(Restrictions.eq("state", State.ENABLE));
        if (filterName != null && filterName.length() > 0)
            detachedCriteria.add(Restrictions.like("nom", "%" + filterName + "%"));

        List result = getHibernateTemplate().findByCriteria(detachedCriteria);
        return result;

    }

    public User getUserByMail(String property) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("email", property));
        List<User> result = (List<User>)getHibernateTemplate().findByCriteria(detachedCriteria);
        if (result.size() > 0) return result.get(0);
        return null;
    }

    public List<User> searchUser(String filter) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery("FROM User WHERE firstName LIKE :nom OR lastName LIKE :prenom ");
        query.setParameter("nom", "%" + filter + "%");
        query.setParameter("prenom", "%" + filter + "%");
        List<User> result = query.list();

        return result;
    }

    public void disable(User user) {
        user.setState(State.DISABLE);
        save(user);
    }

    @Override
    public int disablePhoneActivatedUsers() {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery("update User set activationExpirationDate = null where activationExpirationDate < current_timestamp()");
        return query.executeUpdate();
    }

    @Override
    public User getByIdAndService(String accountID, String username, String service) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq(accountID, username));
        criteria = criteria.createCriteria("services").add(Restrictions.eq("name", service));

        List result = criteria.list();

        if (result.size() == 0)
            return null;
        return (User)result.get(0);
    }

}
