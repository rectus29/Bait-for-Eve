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

import com.rectuscorp.evetool.dao.impl.DaoInvcategories;
import com.rectuscorp.evetool.dao.impl.DaoInvgroups;
import com.rectuscorp.evetool.entities.Invcategories;
import com.rectuscorp.evetool.entities.Invgroups;
import com.rectuscorp.evetool.service.IserviceInvcategories;
import com.rectuscorp.evetool.service.IserviceInvgroups;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceInvcategories")
public class ServiceInvcategories extends GenericManagerImpl<Invcategories, Long> implements IserviceInvcategories {

    private static final Logger log = LogManager.getLogger(ServiceInvcategories.class);

    private DaoInvcategories daoInvcategories;

    @Autowired
    public ServiceInvcategories(DaoInvcategories daoInvcategories) {
        super(daoInvcategories);
        this.daoInvcategories = daoInvcategories;
    }
}
