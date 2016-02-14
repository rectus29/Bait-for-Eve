package com.rectuscorp.evetool.dao;

import com.rectuscorp.evetool.entities.core.GenericEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IncrementGenerator;

import java.io.Serializable;

/**
 * Created by Rectus_29 on 10/02/2016.
 */
public class EveToolIDGenerator extends IncrementGenerator {

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        if(((GenericEntity) o).getId() != null)
            return ((GenericEntity) o).getId();
        return super.generate(sessionImplementor, o);
    }
}
