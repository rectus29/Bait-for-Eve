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

import com.rectuscorp.evetool.dao.impl.DaoInvgroups;
import com.rectuscorp.evetool.entities.Invgroups;
import com.rectuscorp.evetool.service.IserviceInvgroups;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceInvgroups")
public class ServiceInvgroups extends GenericManagerImpl<Invgroups, Long> implements IserviceInvgroups {

    private static final Logger log = Logger.getLogger(ServiceInvgroups.class);

    private DaoInvgroups daoInvgroups;

    @Autowired
    public ServiceInvgroups(DaoInvgroups daoInvgroups) {
        super(daoInvgroups);
        this.daoInvgroups = daoInvgroups;
    }
}
