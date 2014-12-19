package com.rectuscorp.evetool.service.impl;
/*-----------------------------------------------------*/
/*       _____           _               ___   ___     */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 21/04/13 17:10                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectuscorp.evetool.dao.impl.DaoInvmarketgroups;
import com.rectuscorp.evetool.entities.MarketGroup;
import com.rectuscorp.evetool.service.IserviceInvmarketgroups;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceInvmarketgroups")
public class ServiceInvmarketgroups extends GenericManagerImpl<MarketGroup, Long> implements IserviceInvmarketgroups {

    private static final Logger log = Logger.getLogger(ServiceInvmarketgroups.class);

    private DaoInvmarketgroups daoInvmarketgroups;

    @Autowired
    public ServiceInvmarketgroups(DaoInvmarketgroups daoInvmarketgroups) {
        super(daoInvmarketgroups);
        this.daoInvmarketgroups = daoInvmarketgroups;
    }
}
