package com.rectuscorp.evetool.entities.core;

import com.andil.mismacore.entities.Address;
import com.andil.mismacore.entities.Currency;
import com.andil.mismacore.enums.ApplicationScope;
import com.andil.mismacore.enums.OrderState;
import com.andil.mismacore.enums.PaymentMethod;
import com.andil.mismacore.enums.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: ak4t0sh
 * Date: 17/01/13
 * Time: 15:13
 */
@Entity
@Table(name = "store_order")
public class Order extends GenericEntity {

    @OneToMany(mappedBy = "referenceOrder")
    private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

    @ManyToOne
    private User user;

    @Column
    private ApplicationScope scope;

    @Column
    private OrderState state = OrderState.PENDING;

    @Column
    private PaymentMethod paymentMethod = PaymentMethod.SIPS;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String paymentToken="";

    @Column(columnDefinition = "MEDIUMTEXT")
    private String paymentRequest="";

    @Column(columnDefinition = "MEDIUMTEXT")
    private String paymentResponse="";

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Address billingAddress;

    @ManyToOne
    private Address deliveryAdress;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getOrderPrice() {
        BigDecimal res = new BigDecimal(0);

        for (OrderItem orderItem : orderItemList)
            res = res.add(orderItem.getOrderItemPrice());
        return res;
    }
    public BigDecimal getOrderTax() {
        BigDecimal res = new BigDecimal(0);

        for (OrderItem orderItem : orderItemList)
            res = res.add(orderItem.getOrderItemTax());
        return res;
    }

    public BigDecimal getOrderPriceWithTax() {
        BigDecimal res = new BigDecimal(0);

        for (OrderItem orderItem : orderItemList)
            res = res.add(orderItem.getOrderItemPriceWithTax());
        return res;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }
    public List<OrderItem> orderItemFromType(ProductType productType) {
        List<OrderItem> res = new ArrayList<OrderItem>();

        for(OrderItem orderItem : orderItemList){
            if(orderItem.getType().equals(productType))
                res.add(orderItem);
        }
        return res;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(Address deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public ApplicationScope getScope() {
        return scope;
    }

    public void setScope(ApplicationScope scope) {
        this.scope = scope;
    }

    public String getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(String paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public String getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(String paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

    public Order() {
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
