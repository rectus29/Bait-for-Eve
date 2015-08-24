package com.rectuscorp.evetool.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/*-----------------------------------------------------*/
/* User: Rectus_29                 Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
//@Entity
public class InvBlueprintTypes {

    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "blueprintTypeID", referencedColumnName = "typeID")
    private Invtypes invtypes;

    @Column(name = "parentBlueprintTypeID")
    private long parentBlueprintTypeID;

    @OneToOne
    @JoinColumn(name = "productTypeID", referencedColumnName = "typeID")
    private Invtypes productTypeID;
}
