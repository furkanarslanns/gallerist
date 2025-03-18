package com.furkanarslan.gallerist.entitiy;

import com.furkanarslan.gallerist.enums.CarStatusType;
import com.furkanarslan.gallerist.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name ="car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{
    @Column(name = "plaka")
    private String plaka;
    @Column(name = "model")
    private String model;
    @Column(name = "brand")
    private String brand;
    @Column(name = "production_year")
    private int productionYear;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @Column(name = "damage_price")
     private BigDecimal damagePrice;
    @Column(name = "car_status")
    @Enumerated(EnumType.STRING)
     private CarStatusType carStatus;





}
