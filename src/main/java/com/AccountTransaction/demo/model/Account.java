package com.AccountTransaction.demo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Account {

    @Id
    private long id;
    private String name;
    private double balance;

    public Account(){}
    
    public Account(long id,String name,double balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    //balance update methods
    public void depositBalance(double amount){
        balance = balance + amount;
    }
    
    public void withdrawBalance(double amount){
        balance = balance - amount;
    }
    
    //setter for Id
    public void setId(long Id){
        this.id = Id;
    }

    //getters 
    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getBalance(){
        return  this.balance;
    }
}
