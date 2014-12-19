package com.rectuscorp.evetool.service.impl;

import com.rectuscorp.evetool.dao.impl.DaoRole;
import com.rectuscorp.evetool.entities.Role;
import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: olivier
 * Date: 5 juil. 2010
 */

@Service("serviceRole")
public class ServiceRole extends GenericManagerImpl<Role, Long> implements IserviceRole {

    private static final Logger log = Logger.getLogger(ServiceRole.class);

    private DaoRole daoRole;

    @Autowired
    public ServiceRole(DaoRole daoRole) {
        super(daoRole);
        this.daoRole = daoRole;
    }


    public Role getRoleByName(String roleName) {
        return daoRole.getRoleByName(roleName);
    }

    public Role getRoleByDesc(String desc) {
        return daoRole.getRoleByDesc(desc);
    }

    public List<Role> getAuthorizedRole(User u) {
        return daoRole.getAuthorizedRole(u);
    }
}
