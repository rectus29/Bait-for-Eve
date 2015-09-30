package com.rectuscorp.evetool.entities;

/*-----------------------------------------------------*/
/* User: Rectus_29 for Andil      Date: 27/01/15 18:28 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import com.rectuscorp.evetool.enums.State;

import java.io.Serializable;

public interface Individual extends Serializable {

    public Long getId();
    public String getFormattedName();
    public String getLastName();
    public String getFirstName();
    public State getState();
    public String getAvatarPath();
}
