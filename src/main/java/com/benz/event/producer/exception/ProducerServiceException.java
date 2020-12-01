package com.benz.event.producer.exception;

import lombok.Data;

/* (non-Javadoc)
 * @see java.lang.Throwable#toString()
 */
@Data
public class ProducerServiceException extends RuntimeException {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The custom code. */
    
    /**
     * Instantiates a new producer service validation exception.
     *
     * @param message the message
     * @param statuscode the custom code
     */
    public ProducerServiceException(String message) {
        super(message);
    }
}
