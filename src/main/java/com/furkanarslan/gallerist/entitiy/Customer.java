package com.furkanarslan.gallerist.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends  BaseEntity{
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String tckn;
    @Column
    private Date birthOfDate;
    @OneToOne
    private Address address;
    @OneToOne
    private Account account;
}
