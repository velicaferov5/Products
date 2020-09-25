package com.products.api.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Integer id;

    @ElementCollection
    @Column
    private Map<Product, Integer> products;

    @Column
    private State state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        NOT_AVAILABLE,
        RUNNING,
        DELIVERED,
        CANCELLED
    }
}
