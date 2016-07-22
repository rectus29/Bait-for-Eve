package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoConfig;
import com.rectuscorp.evetool.dao.IdaoGroup;
import com.rectuscorp.evetool.entities.core.Config;
import com.rectuscorp.evetool.entities.crest.Group;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rectus_29
 * Date: 11 avr. 2010
 * Time: 00:34:16
 * To change this template use File | Settings | File Templates.
 */
@Repository("daoGroup")
public class DaoGroup extends GenericDaoHibernate<Group, Long> implements IdaoGroup {


    public DaoGroup() {
        super(Group.class);
    }


}