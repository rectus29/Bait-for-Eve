package com.rectuscorp.evetool.entities.core;


import com.rectuscorp.evetool.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: rectus_29
 * Date: 10/01/13
 * Time: 11:04
 */
@Entity
@Table(name = "store_cart")
public class Cart extends GenericEntity {

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();

    @ManyToOne
    private User user;
    @Column
    private State state = State.ENABLE;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    public Cart() {
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }



    public void addProduct(CartProduct cp) {
        boolean found = false;
        for (CartProduct temp : cartProducts)
            if (temp.equals(cp)) {
                temp.setQuantity(temp.getQuantity() + cp.getQuantity());
                found = true;
                break;
            }
        if (!found)
            this.cartProducts.add(cp);
    }

    public ArrayList<CartProduct> getActiveCartProducts() {
        ArrayList<CartProduct> res = new ArrayList<CartProduct>();
        for (CartProduct cartProduct : cartProducts) {
            if (cartProduct.getQuantity() > 0l)
                res.add(cartProduct);
        }
        return res;
    }


}
