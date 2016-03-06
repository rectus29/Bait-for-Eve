package com.rectuscorp.evetool.entities.core;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: rectus_29
 * Date: 10/01/13
 * Time: 11:04
 */
@Entity
@Table(name = "store_cart_product")
public class CartProduct extends GenericEntity {

//    @ManyToOne
//    private Product product;
    @ManyToOne
    private Cart cart;

    @Column
    private Long quantity = 1l;


    public CartProduct() {
    }



    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }



}
