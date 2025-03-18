package com.furkanarslan.gallerist.entitiy;

import com.furkanarslan.gallerist.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    @Column(name = "accountNo")
    private String accountNo;
    @Column(name = "iban")
    private String iban;
    @Column(name = "amount")
    private String amount;
    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

}
