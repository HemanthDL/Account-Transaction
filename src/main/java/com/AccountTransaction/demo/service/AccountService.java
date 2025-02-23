package com.AccountTransaction.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AccountTransaction.demo.model.Account;
import com.AccountTransaction.demo.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository repo;

    private long count = 101;


    public List<Account> getAccounts(){
        return repo.findAll();
    }

    public  Account getAccountById(long id){
        return repo.findById(id).orElse(null);
    }

    public Account addAccount(Account account){
        account.setId(count);
        count++;
        repo.save(account);
        return account;
    }

    public boolean deleteAccount(long id){
        if(!repo.existsById(id))
            return false;

        repo.deleteById(id);

        return true;
    }

    public Account depositToAccount(long id,double balance) {

        Account account = getAccountById(id);

        if(account == null){
            return account;
        }

        

        account.depositBalance(balance);

        repo.save(account);

        return account;
    }

    public Account withdrawFromAccount(long id,double balance) {

        Account account = getAccountById(id);

        
        if(account == null){
            return account;
        }

        if(account.getBalance() < balance){
            return account;
        }
        
        account.withdrawBalance(balance);

        repo.save(account);

        return account;
    }
}
