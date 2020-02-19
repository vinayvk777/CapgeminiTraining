package groupone;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.*;
class TransactionException extends Exception {
    
    String errorMessage, errorCode;

    TransactionException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode(){
        return errorCode;
    }

}
class DigitalWallet{
    String walletId, userName, userAccessCode;
    int walletBalance;

    public DigitalWallet(String walletId, String userName){
        this.walletId = walletId;
        this.userName = userName;
    }

    public DigitalWallet(String walletId, String userName, String userAccessCode){
        this.walletId = walletId;
        this.userName = userName;
        this.userAccessCode = userAccessCode;
    }

    public String getWalletId(){
        return walletId;
    }

    public String getUsername(){
        return userName;
    }

    public String getUserAccessToken(){
        return userAccessCode;
    }

    public int getWalletBalance(){
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance){
        this.walletBalance = walletBalance;
    }

}
class DigitalWalletTransaction{
	
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
public class Solution {
	
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final DigitalWalletTransaction DIGITAL_WALLET_TRANSACTION = new DigitalWalletTransaction();
    
    private static final Map<String, DigitalWallet> DIGITAL_WALLETS = new HashMap<>();
    
    public static void main(String[] args) {
    	System.out.println("Enter Number of Accounts  ");
        int numberOfWallets = Integer.parseInt(INPUT_READER.nextLine());
    	System.out.println("Enter User_ID User_name User_AccessCode");

        while (numberOfWallets-- > 0) {
            String[] wallet = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet;
            
            if (wallet.length == 2) {
                digitalWallet = new DigitalWallet(wallet[0], wallet[1]);
            } else {
                digitalWallet = new DigitalWallet(wallet[0], wallet[1], wallet[2]);
            }
            
            DIGITAL_WALLETS.put(wallet[0], digitalWallet);
        }

        System.out.println("Enter Number of Transactions  ");
        int numberOfTransactions = Integer.parseInt(INPUT_READER.nextLine());
        System.out.println("You are ready to pay or add money");
        while (numberOfTransactions-- > 0) {
            String[] transaction = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet = DIGITAL_WALLETS.get(transaction[0]);
            
            if (transaction[1].equals("add")) {
                try {
                    DIGITAL_WALLET_TRANSACTION.addMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("₹ "+transaction[2]+" Credited to Wallet Successfully.");
                    System.out.println("Total Balance= ₹"+digitalWallet.getWalletBalance());
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            } else {
                try {
                    DIGITAL_WALLET_TRANSACTION.payMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("₹ "+transaction[2]+" Debited from the Wallet Successfully.");
                    System.out.println("Total Balance= ₹"+digitalWallet.getWalletBalance());
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            }
        }
        
        System.out.println();
        System.out.println("Account Details & Final Balance ");
        DIGITAL_WALLETS.keySet()
                .stream()
                .sorted()
                .map((digitalWalletId) -> DIGITAL_WALLETS.get(digitalWalletId))
                .forEachOrdered((digitalWallet) -> {
                    System.out.println(digitalWallet.getWalletId()
                            + " " + digitalWallet.getUsername()
                            + " " + digitalWallet.getWalletBalance());
                });
    }
}
