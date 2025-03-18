package com.furkanarslan.gallerist.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="saled_car",
uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id","customer_id"},
name = "up_gallerist_car_customer")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaledCar extends BaseEntity{
    @ManyToOne
    private Car car;
    @ManyToOne
    private  Gallerist gallerist;
    @ManyToOne
    private Customer customer;

}
