package ru.javawebinar.restavoter.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dateTime DESC")
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
