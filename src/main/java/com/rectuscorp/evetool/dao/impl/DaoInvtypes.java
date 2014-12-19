package com.rectuscorp.evetool.dao.impl;
/*-----------------------------------------------------*/
/*       _____           _               ___   ___     */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 21/04/13 17:16                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectuscorp.evetool.dao.IdaoInvtypes;
import com.rectuscorp.evetool.entities.Invtypes;
import org.springframework.stereotype.Repository;

@Repository("DaoInvtypes")
public class DaoInvtypes extends GenericDaoHibernate<Invtypes, Long> implements IdaoInvtypes {

    public DaoInvtypes() {
        super(Invtypes.class);
    }
}
