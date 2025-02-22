package com.AccountTransaction.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Amount {
    private double amount;

    @JsonCreator
    public Amount(@JsonProperty("amount") double amount){
        this.amount = amount;
    }

    public double getBalance(){
        return this.amount;
    }
}
