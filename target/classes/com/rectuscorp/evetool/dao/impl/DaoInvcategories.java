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

import com.rectuscorp.evetool.dao.IdaoInvcategories;
import com.rectuscorp.evetool.entities.Invcategories;
import com.rectuscorp.evetool.entities.Invgroups;
import org.springframework.stereotype.Repository;

@Repository("DaoInvcategories")
public class DaoInvcategories extends GenericDaoHibernate<Invcategories, Long> implements IdaoInvcategories {

    public DaoInvcategories() {
        super(Invcategories.class);
    }
}
