package com.rectuscorp.evetool.dao;


import com.rectuscorp.evetool.entities.User;

import java.util.List;

/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public interface IdaoUser extends GenericDao<User, Long> {

    public User getUserByName(String username);

    public User getUserByUsername(String username);

    public List getUsers(int first, int count, String sortProperty, boolean sortAsc, String filterName);

    public List<User> getUsersByFilter(String filter);

    public User getUserByMail(String property);

    public List<User> searchUser(String filter);

    public void disable(User user);

    public int disablePhoneActivatedUsers();

    public User getByIdAndService(String accountID, String username, String service);

}
