package com.products.api.model;

import javax.persistence.*;

@Entity
@Table(name="Stock")
public class Stock {

    @Id
    @Column
    private Integer id;
    @Column
    private Long amount;
    @OneToOne
    @MapsId
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
