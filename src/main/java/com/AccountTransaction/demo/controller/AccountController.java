package com.AccountTransaction.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.AccountTransaction.demo.model.Account;
import com.AccountTransaction.demo.model.Amount;
import com.AccountTransaction.demo.model.CustomException;
import com.AccountTransaction.demo.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService service;


    @GetMapping("/api/accounts")
    public List<Account> getAccounts(){
        return service.getAccounts();
    }

    @GetMapping("/api/accounts/{accId}")
    public ResponseEntity<?> getAccountById(@PathVariable long accId){
       try {
            Account account = service.getAccountById(accId);

            if(account == null){
                throw new Exception("Account Not Found");
            }

            return new ResponseEntity<>(account, HttpStatus.OK);

       } catch (Exception e) {

            System.out.println("error : "+e.getMessage());
            
            return new ResponseEntity<>(
                new CustomException("Account Not found","uri=/api/accounts/"+accId,"ACCOUNT_NOT_FOUND"),HttpStatus.NOT_FOUND
            );
       }
    }

    @PostMapping("/api/accounts")
    public ResponseEntity<?> addAccount(@RequestBody Account account){
        Account resAccount = service.addAccount(account);

        return new ResponseEntity<>(resAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/accounts/{accId}")
    public ResponseEntity<?> deleteAccount(@PathVariable long accId){

        try {
            boolean response = service.deleteAccount(accId);

            if(!response){
                throw new Exception("Account Not Found");
            }

            return new ResponseEntity<>("Account deleted", HttpStatus.OK);

       } catch (Exception e) {

            System.out.println("error : "+e.getMessage());
            
            return new ResponseEntity<>(
                new CustomException("Account Not found","uri=/api/accounts/"+accId,"ACCOUNT_NOT_FOUND"),HttpStatus.NOT_FOUND
            );
       }
        
    }

    @PutMapping("/api/accounts/{accId}/deposit")
    public  ResponseEntity<?> depositToAccount(@PathVariable long accId,@RequestBody Amount amount){

        try {

            Account account = service.depositToAccount(accId,amount.getBalance());

            if(account == null){
                throw new Exception("Account Not Found");
            }

            return new ResponseEntity<>(account, HttpStatus.OK);
            
        } catch (Exception e) {
            System.out.println("error : "+e.getMessage());
            
            return new ResponseEntity<>(
                new CustomException("Account Not found","uri=/api/accounts/"+accId,"ACCOUNT_NOT_FOUND"),HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/api/accounts/{accId}/withdraw")
    public ResponseEntity<?> withdrawFromAccount(@PathVariable long accId,@RequestBody Amount amount){
        try {

            Account account = service.withdrawFromAccount(accId,amount.getBalance());

            if(account == null){
                throw new Exception("Account Not Found");
            }

            if(account.getBalance() < amount.getBalance()){
                return new ResponseEntity<>(
                    new CustomException("InSufficient Balance", "/api/accounts/"+accId, "INSUFFIENT_BALANCE"),HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(account, HttpStatus.OK);
            
        } catch (Exception e) {
            System.out.println("error : "+e.getMessage());
            
            return new ResponseEntity<>(
                new CustomException("Account Not found","uri=/api/accounts/"+accId,"ACCOUNT_NOT_FOUND"),HttpStatus.NOT_FOUND
            );
        }
    }
}
