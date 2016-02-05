package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoPermission;
import com.rectuscorp.evetool.entities.core.Permission;
import org.springframework.stereotype.Repository;

/**
 * Created by Oliv'Generator
 * User: Olivier
 * Date: 12 janv. 2012
 * Time: 11:32:36
 */

@Repository("daoPermission")
public class DaoPermission extends GenericDaoHibernate<Permission, Long> implements IdaoPermission {

    public DaoPermission() {
        super(Permission.class);
    }
}
