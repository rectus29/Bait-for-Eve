package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoCorporation;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.tools.EveXmlApi;
import org.springframework.stereotype.Repository;

/**
 * User: Rectus_29
 * Date: 10/02/16
 */
@Repository("daoCorporation")
public class DaoCorporation extends GenericDaoHibernate<Corporation, Long> implements IdaoCorporation {

	public DaoCorporation() {
		super(Corporation.class);
	}

	@Override
	public Corporation get(Long id) {
		Corporation out = super.get(id);
		if (out == null) {
			out = EveXmlApi.get().getCorporation(id.toString());
			if (out != null) {
				out = save(out);
			}
		}
		return out;
	}
}