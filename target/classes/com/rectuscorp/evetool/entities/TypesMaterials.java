package com.rectuscorp.evetool.entities;

/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus_29 for        Date: 22/05/13 12:30 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import javax.persistence.*;

@Entity
@Table(name = "invtypematerials")
@IdClass(TypesMaterials_PK.class)
public class TypesMaterials  {

    @Id
    @ManyToOne
    @JoinColumn(name = "typeID")
    private Invtypes typeID;

    @Id
    @ManyToOne
    @JoinColumn(name = "materielTypeID")
    private Invtypes material;

    @Column
    private int quantity;



}
