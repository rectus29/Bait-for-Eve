package com.rectuscorp.evetool.service.impl;

import com.rectuscorp.evetool.dao.impl.DaoConfig;
import com.rectuscorp.evetool.dao.impl.DaoGroup;
import com.rectuscorp.evetool.entities.core.Config;
import com.rectuscorp.evetool.entities.crest.Group;
import com.rectuscorp.evetool.service.IserviceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: rectus_29
 * Date: 16 mars 2010
 * Time: 11:25:42
 * To change this template use File | Settings | File Templates.
 */
@Service("serviceGroup")
public class ServiceGroup extends GenericManagerImpl<Group, Long> implements IserviceGroup {

    private DaoGroup daoGroup;

    @Autowired
    public ServiceGroup(DaoGroup daoGroup) {
        super(daoGroup);
        this.daoGroup = daoGroup;
    }
}