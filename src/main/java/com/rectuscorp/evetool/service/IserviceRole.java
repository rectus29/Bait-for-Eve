package com.rectuscorp.evetool.service;

import com.rectuscorp.evetool.entities.Role;
import com.rectuscorp.evetool.entities.User;

import java.util.List;

/**
 * User: olivier
 * Date: 5 juil. 2010
 */
public interface IserviceRole extends GenericManager<Role, Long> {
    public Role getRoleByName(String roleName);

    public Role getRoleByDesc(String desc);

    public List<Role> getAuthorizedRole(User u);


}
