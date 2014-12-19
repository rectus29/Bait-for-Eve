package com.rectuscorp.evetool.entities;

/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus_29 for Andil       Date: 22/05/13 12:30 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

//@Table(name = "InvtypesHistory")
public class InvTypesHistory extends GenericEntity {

    //@ManyToOne
    private Invtypes invtypes;

   // @Column
    private Date date;



}
