package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoType;
import com.rectuscorp.evetool.entities.core.Config;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.entities.crest.Type;
import com.rectuscorp.evetool.tools.EveCRESTApi;
import com.rectuscorp.evetool.tools.EveXmlApi;
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
@Repository("daoType")
public class DaoType extends GenericDaoHibernate<Type, Long> implements IdaoType {

	public DaoType() {
		super(Type.class);
	}

	@Override
	public Type get(Long id) {
		Type out = super.get(id);
		if (out == null) {
			out = EveCRESTApi.get().getType(id.toString());
			if (out != null) {
				out = save(out);
			}
		}
		return out;
	}

}