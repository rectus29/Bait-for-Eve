package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoGeneric;
import com.rectuscorp.evetool.entities.core.GenericEntity;
import org.springframework.stereotype.Repository;


/**
 * User: Rectus_29
 * Date: 10/02/16
 */
@Repository("daoGeneric")
public class DaoGeneric extends GenericDaoHibernate<GenericEntity, Long> implements IdaoGeneric {


    public DaoGeneric() {
        super(GenericEntity.class);
    }


}