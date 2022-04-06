package com.example.bankaccount.query.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Getter @Setter @Id private String accountId;
    @Getter @Setter @Column private Long balance;
    @Getter @Setter @Column private String status;
    @Getter @Setter @Column private boolean isEnabled;

}
