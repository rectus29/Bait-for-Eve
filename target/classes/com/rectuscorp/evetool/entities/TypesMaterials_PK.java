package com.rectuscorp.evetool.entities;
/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 22/06/13 14:29                 */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class TypesMaterials_PK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "typeID")
    private Invtypes typeID;

    @Id
    @ManyToOne
    @JoinColumn(name = "materielTypeID")
    private Invtypes material;

}
