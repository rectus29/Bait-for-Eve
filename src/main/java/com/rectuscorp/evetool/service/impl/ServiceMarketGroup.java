package com.rectuscorp.evetool.service.impl;

import com.rectuscorp.evetool.api.EveCRESTApi;
import com.rectuscorp.evetool.dao.impl.DaoMarketGroup;
import com.rectuscorp.evetool.dao.impl.DaoType;
import com.rectuscorp.evetool.entities.crest.MarketGroup;
import com.rectuscorp.evetool.entities.crest.Type;
import com.rectuscorp.evetool.service.IserviceMarketGroup;
import com.rectuscorp.evetool.service.IserviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 12/10/2016 10:46               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Service("serviceMarketGroup")
public class ServiceMarketGroup extends GenericManagerImpl<MarketGroup, Long> implements IserviceMarketGroup {

	private DaoMarketGroup daoMarketGroup;

	@Autowired
	public ServiceMarketGroup(DaoMarketGroup daoMarketGroup) {
		super(daoMarketGroup);
		this.daoMarketGroup = daoMarketGroup;
	}

	@Override
	public MarketGroup get(Long id) {
		MarketGroup out = super.get(id);
		if (out == null) {
			out = EveCRESTApi.get().getMarketGroup(id.toString());
			if (out != null) {
				out = save(out);
			}
		}
		return out;
	}

}