package br.com.comexport.exception;

public class CustomException {
	 
    private String defaultMessage;
 
    public CustomException(String defaultMessage){
        this.defaultMessage = defaultMessage;
    }
 
    public String getDefaultMessage() {
        return defaultMessage;
    }
 
}