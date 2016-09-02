package com.rectuscorp.evetool.entities.core;

import com.rectuscorp.evetool.entities.crest.Type;

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

    @ManyToOne
    private Type type;
    @ManyToOne
    private Cart cart;
    @Column
    private Long quantity = 1l;
	@Column
	private Long price = 1l;

	public CartProduct(Type type,  Long quantity, Long price) {
		this.type = type;
		this.quantity = quantity;
		this.price = price;
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

	public Type getType() {
		return type;
	}

	public CartProduct setType(Type type) {
		this.type = type;
		return this;
	}

	public Long getPrice() {
		return price;
	}

	public CartProduct setPrice(Long price) {
		this.price = price;
		return this;
	}
}
