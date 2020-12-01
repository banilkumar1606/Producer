package com.benz.event.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Data

/**
 * Instantiates a new event.
 *
 * @param fuellid the fuellid
 * @param city the city
 */
@AllArgsConstructor

/**
 * Instantiates a new event.
 */
@NoArgsConstructor

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@ToString
public class Event{
	
	/** The fuellid. */
	private Boolean fuellid;
	
	/** The city. */
	private String city;
	
	/** The trace ID. */
	private String traceID;
}
