/**
 * 
 */
package com.mzsanford.cube;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Matt Sanford <matt@mzsanford.com>
 *
 */
public interface CubeClient {

    /**
     * Send a single Event to the Cube server
     * 
     * @param event
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public void send(Event event) throws JsonGenerationException, JsonMappingException, IOException;
}
