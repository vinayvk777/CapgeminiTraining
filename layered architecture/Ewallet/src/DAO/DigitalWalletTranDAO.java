package DAO;
import  model.*;
import exception.*;
public class DigitalWalletTranDAO{
    public void addMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
        if (digitalWallet.getUserAccessToken() == null){
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        } else if(amount <=0){
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        } else {
            digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() + amount);
        }
    }

    public void payMoney(DigitalWallet digitalWallet, int amount) throws TransactionException{
        if(digitalWallet.getWalletBalance()<amount){
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");
        } else if (digitalWallet.getUserAccessToken() == null){
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        } else if(amount <= 0){
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        } else{
            digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() - amount);
        }
    }
}
