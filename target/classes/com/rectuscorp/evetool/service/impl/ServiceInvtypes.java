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

import com.rectuscorp.evetool.dao.impl.DaoInvtypes;
import com.rectuscorp.evetool.entities.Invtypes;
import com.rectuscorp.evetool.service.IserviceInvtypes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceInvtypes")
public class ServiceInvtypes extends GenericManagerImpl<Invtypes, Long> implements IserviceInvtypes {

    private static final Logger log = Logger.getLogger(ServiceInvtypes.class);

    private DaoInvtypes daoInvtypes;

    @Autowired
    public ServiceInvtypes(DaoInvtypes daoInvtypes) {
        super(daoInvtypes);
        this.daoInvtypes = daoInvtypes;
    }
}
