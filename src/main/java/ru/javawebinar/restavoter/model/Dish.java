package ru.javawebinar.restavoter.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, @NotNull Integer price, @NotNull Date dateTime, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }
}
