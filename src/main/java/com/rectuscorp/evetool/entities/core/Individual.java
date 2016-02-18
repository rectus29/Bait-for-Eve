package com.rectuscorp.evetool.entities.core;


/*-----------------------------------------------------*/
/*                    by Rectus_29                     */
/*                Date: 18/02/16 11:20                 */
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
