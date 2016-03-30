
package com.codinginfinity.research.report;
public class InvalidRequestException  extends Exception{
    
    /**
     * Constructs a new InvalidRequestException with null as its detail message.
     */
    public InvalidRequestException(){
    }
	
    /**
     * Constructs a new InvalidRequestException with the specified detail message
     * @param message 
     */
    public InvalidRequestException(String message){
        super(message);
    }
    
    /**
     * Constructs a new InvalidRequestException with the specified detail message and cause.
     * @param message
     * @param cause 
     */
    public InvalidRequestException(String message, Throwable cause){
        super(message, cause);
    }
    
    /**
     * Constructs a new InvalidRequestException with the specified cause and a detail message of cause.toString().
     * @param cause 
     */
    public InvalidRequestException(Throwable cause){
        super(cause);
    }

    
    
}
