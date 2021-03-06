package com.rectuscorp.evetool.dao;


import com.rectuscorp.evetool.entities.core.Config;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public interface IdaoConfig extends GenericDao<Config, Long> {
    public Config getByKey(String key);
}
