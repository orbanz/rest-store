package com.orbanz.store.order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderData {
    @Id
    @GeneratedValue
    private Long id;

    private String buyerEmail;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date orderPlaced;

    @OneToMany(
            mappedBy = "orderData",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<OrderItem>();

    public double getTotalOrderAmount() {
        return items.stream().mapToDouble(item -> item.getPrice()).sum();
    }

    public void addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
        orderItem.setOrderData(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        items.remove(orderItem);
        orderItem.setOrderData(null);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderData() {
    }

    public OrderData(String buyerEmail, Date orderPlaced) {
        this.buyerEmail = buyerEmail;
        this.orderPlaced = orderPlaced;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Date getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(Date orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
