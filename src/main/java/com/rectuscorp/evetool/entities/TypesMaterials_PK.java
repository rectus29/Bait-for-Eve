package com.rectuscorp.evetool.entities;


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
