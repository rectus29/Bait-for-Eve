package com.rectuscorp.evetool.entities;

import javax.persistence.*;

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
