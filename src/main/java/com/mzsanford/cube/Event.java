/**
 * 
 */
package com.mzsanford.cube;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * @author Matt Sanford <matt@mzsanford.com>
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class Event {
    private String id;
    private Date date;
    private String type;
    private Map<String, String> data;

    /**
     * Create a new Event object with the default time (now) and id (null)
     * 
     * @param type
     */
    public Event(@JsonProperty("type") String type) {
        if (null == type) {
            throw new IllegalArgumentException("Type must not be null");
        }
        this.id = null;
        this.type = type.replaceAll("[^\\w\\d]", "_");
        this.date = new Date();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(Map<String, String> data) {
        // TODO: Only allows a single level of data, not a tree.
        this.data = data;
    }


    /**
     * Generate the JSON representation of this Event
     * 
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String toJSON() throws JsonGenerationException,
            JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.setDateFormat(formatter);

        return mapper.writeValueAsString(this);
    }

}
