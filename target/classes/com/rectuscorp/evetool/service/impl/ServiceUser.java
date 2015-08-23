package com.rectuscorp.evetool.service.impl;

import com.rectuscorp.evetool.dao.impl.DaoUser;
import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Olivier
 * Date: 20 janv. 2010
 * Time: 10:50:30
 * To change this template use File | Settings | File Templates.
 */
@Service("serviceUser")
public class ServiceUser extends GenericManagerImpl<User, Long> implements IserviceUser {

    private static final Logger log = Logger.getLogger(ServiceUser.class);

    private DaoUser daoUser;

    @Autowired
    public ServiceUser(DaoUser daoUser) {
        super(daoUser);
        this.daoUser = daoUser;
    }


    public User getCurrentUser() {
        if (SecurityUtils.getSubject().getPrincipals().getRealmNames().contains("org.apache.shiro.realm.ldap.JndiLdapRealm_1")) {
            return getUserLDAP(SecurityUtils.getSubject());
        } else {
            return getUser(SecurityUtils.getSubject());
        }

    }

    public User getUser(Subject subject) {
        Long id = (Long) subject.getPrincipal();
        if (id != null) {
            // they are either authenticated or remembered from a previous com..mismastore.session,
            // so return the user:
            return get(id);
        } else {
            //not logged in or remembered:
            return null;
        }
    }

    public User getUserLDAP(Subject subject) {
        String userName = (String) subject.getPrincipal();
        if (userName != null) {
            // they are either authenticated or remembered from a previous com..mismastore.session,
            // so return the user:
            return getUserByUsername(userName);
        } else {
            //not logged in or remembered:
            return null;
        }
    }

    public List<User> getUsersByFilter(String filter) {
        return daoUser.getUsersByFilter(filter);
    }


    public User getUserByUsername(String username) {
        return daoUser.getUserByUsername(username);
    }

    public User getUserByName(String nomUser) {
        return daoUser.getUserByName(nomUser);
    }

    public List getUsers(int first, int count, String sortProperty, boolean sortAsc, String nameFilter) {
        return daoUser.getUsers(first, count, sortProperty, sortAsc, nameFilter);
    }


    public List getUsers(int first, int count, String sortProperty, boolean sortAsc) {
        return daoUser.getUsers(first, count, sortProperty, sortAsc, "");
    }


    public User save(User c) {
        return daoUser.save(c);
    }

    public void disable(User user) {
        daoUser.disable(user);
    }

    @Override
    public int disablePhoneActivatedUsers() {
        return daoUser.disablePhoneActivatedUsers();
    }

    @Override
    public User getByIdAndService(String accountID, String username, String service) {
        return daoUser.getByIdAndService(accountID, username,service );
    }

    public User getUserByMail(String property) {
        return daoUser.getUserByMail(property);
    }

    public List<User> searchUser(String filter) {
        return daoUser.searchUser(filter);
    }


}
