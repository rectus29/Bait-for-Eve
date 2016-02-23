package com.rectuscorp.evetool.dao.impl;

import com.rectuscorp.evetool.dao.IdaoCorporation;
import com.rectuscorp.evetool.entities.crest.Corporation;
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

}