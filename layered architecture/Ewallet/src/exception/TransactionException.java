package exception;

public class TransactionException extends Exception {
    
    String errorMessage, errorCode;

    public TransactionException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode(){
        return errorCode;
    }

}
