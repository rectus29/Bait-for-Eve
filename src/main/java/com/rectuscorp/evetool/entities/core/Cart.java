package com.rectuscorp.evetool.entities.core;

import com.andil.mismacore.entities.Shop;
import com.andil.mismacore.enums.ApplicationScope;
import com.andil.mismacore.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: ak4t0sh
 * Date: 10/01/13
 * Time: 11:04
 */
@Entity
@Table(name = "store_cart")
public class Cart extends GenericEntity {

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
    @Column
    private ApplicationScope scope;
    @ManyToOne
    private User user;
    @Column
    private State state = State.ENABLE;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    public Cart() {
    }

    public Cart(User user) {
        setUser(user);
        setState(State.ENABLE);
    }

    public Cart(User user, ApplicationScope scope) {
        setUser(user);
        setScope(scope);
        setState(State.ENABLE);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public boolean isEnable() {
        return state.equals(State.ENABLE);
    }

    public void addProduct(Shop s, Product p, long q) {
        boolean found = false;
        for (CartProduct temp : cartProducts)
            if (temp.getProduct().equals(p) && temp.getShop().equals(s)) {
                temp.setQuantity(temp.getQuantity() + q);
                found = true;
                break;
            }
        if (!found)
            this.cartProducts.add(new CartProduct(this, p, s, q));
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

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();

        for (CartProduct cartProduct : cartProducts) {
            if (cartProduct.getQuantity() > 0)
                products.add(cartProduct.getProduct());
        }
        return products;
    }

    public CartProduct getCartProduct(Product product) {

        for (CartProduct cartProduct : cartProducts) {
            if (cartProduct.getProduct().equals(product))
                return cartProduct;
        }
        return null;
    }

    public CartProduct getCartProduct(Product product, Shop shop) {

        for (CartProduct cartProduct : cartProducts) {
            if (cartProduct.getProduct().equals(product) && cartProduct.getShop().equals(shop))
                return cartProduct;
        }
        return null;
    }
    public BigDecimal getCartPrice() {
        BigDecimal res = new BigDecimal(0);

        for (CartProduct cartProduct : cartProducts)
            res = res.add(cartProduct.getCartProductPrice());
        return res;
    }
    public BigDecimal getCartPriceWithTax() {
        BigDecimal res = new BigDecimal(0);

        for (CartProduct cartProduct: cartProducts)
            res = res.add(cartProduct.getCartProductPriceWithTax());
        return res;
    }
    public BigDecimal getCartTax() {
         BigDecimal res = new BigDecimal(0);

        for (CartProduct cartProduct: cartProducts)
            res = res.add(cartProduct.getCartProductTax());
        return res;
    }

    public ApplicationScope getScope() {
        return scope;
    }

    public void setScope(ApplicationScope scope) {
        this.scope = scope;
    }
}
