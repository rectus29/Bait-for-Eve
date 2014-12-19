package com.rectuscorp.evetool.service;

import com.rectuscorp.evetool.entities.User;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Olivier
 * Date: 20 janv. 2010
 * Time: 10:49:57
 */
public interface IserviceUser extends GenericManager<User, Long> {

    public User getCurrentUser();

    public User getUser(Subject subject);

    public User getUserByUsername(String groupName);

    public User getUserByName(String groupName);

    public List<User> getUsersByFilter(String filter);

    public List getUsers(int first, int count, String sortProperty, boolean sortAsc);

    public List getUsers(int first, int count, String sortProperty, boolean sortAsc, String filterName);

    public User getUserByMail(String property);

    public List<User> searchUser(String filter);

    public void disable(User user);

    int disablePhoneActivatedUsers();

    User getByIdAndService(String accountID, String username, String service);
}
