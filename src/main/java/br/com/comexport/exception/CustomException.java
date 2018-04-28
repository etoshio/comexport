package br.com.comexport.exception;

public class CustomException {
	 
    private String errorMessage;
 
    public CustomException(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}