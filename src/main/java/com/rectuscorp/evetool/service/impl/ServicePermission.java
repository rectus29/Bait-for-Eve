package com.rectuscorp.evetool.service.impl;

import com.rectuscorp.evetool.dao.impl.DaoPermission;
import com.rectuscorp.evetool.entities.core.Permission;
import com.rectuscorp.evetool.service.IservicePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oliv'Generator
 * User: rectus_29
 * Date: 12 janv. 2012
 * Time: 11:32:36
 */

@Service("servicePermission")
public class ServicePermission extends GenericManagerImpl<Permission, Long> implements IservicePermission {


    private DaoPermission daoPermission;

    @Autowired
    public ServicePermission(DaoPermission daoPermission) {
        super(daoPermission);
        this.daoPermission = daoPermission;
    }


    public Permission save(Permission c) {
        return daoPermission.save(c);
    }

}

