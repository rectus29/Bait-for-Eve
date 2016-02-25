package com.rectuscorp.evetool.entities.core;

import com.andil.mismacore.entities.Shop;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: ak4t0sh
 * Date: 10/01/13
 * Time: 11:04
 */
@Entity
@Table(name = "store_cart_product")
@NamedQueries({
        @NamedQuery(name = "cartproduct.countactiveproduct", query = "SELECT COUNT (cp) FROM CartProduct cp WHERE cp.cart=:cart AND cp.quantity!=0")
})
public class CartProduct extends GenericEntity {

    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Shop shop;
    @Column
    private Long quantity = 1l;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();


    public CartProduct() {
    }

    public CartProduct(Cart cart, Product product) {
        setCart(cart);
        setProduct(product);
        setQuantity(1l);
    }

    public CartProduct(Cart cart, Product product, Long quantity) {
        setCart(cart);
        setProduct(product);
        setQuantity(quantity);
    }

    public CartProduct(Cart cart, Product product, Shop s, Long quantity) {
        setCart(cart);
        setProduct(product);
        setQuantity(quantity);
        setShop(s);
    }

    public CartProduct(Cart cart, Product product, Shop shop) {
        this.product = product;
        this.cart = cart;
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    public BigDecimal getCartProductPrice() {
        return getProduct().getPrice().multiply(new BigDecimal(getQuantity()));
    }
    public BigDecimal getCartProductPriceWithTax() {
        return getProduct().getPriceWithTax().setScale(3, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(getQuantity()));
    }
    public BigDecimal getCartProductTax() {
        return getProduct().getPrice().multiply(getProduct().getTax()).setScale(3, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(getQuantity()));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CartProduct)
            if (((CartProduct) o).getId() != null) {
                if ((((CartProduct) o).getId()).equals(this.getId())) {
                    return true;
                }
            } else if (((CartProduct) o).getProduct().equals(this.getProduct()) && ((CartProduct) o).getShop().equals(this.getShop()))
                return true;
        return false;

    }
}
