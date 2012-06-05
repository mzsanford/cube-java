/**
 * 
 */
package com.mzsanford.cube;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzsanford.cube.Event;

import junit.framework.TestCase;

/**
 * @author Matt Sanford <matt@mzsanford.com>
 *
 */
public class EventTest extends TestCase {

    /**
     * Test method for {@link com.mzsanford.cube.Event#Event(java.lang.String)}.
     */
    public void testEvent() {
        Event event = new Event("typeHere");
        assertEquals("typeHere", event.getType());
        assertNull(event.getId());
    }
    
    public void testEventTypeCheck() {
        assertEquals( "foo_bar", new Event("foo bar").getType() );
        assertEquals( "foo_bar", new Event("foo.bar").getType() );
        assertEquals( "foo_bar", new Event("foo+bar").getType() );
        assertEquals( "foo_bar", new Event("foo-bar").getType() );
    }

    /**
     * Test method for {@link com.mzsanford.cube.Event#toJSON()}.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public void testToJSON() throws JsonGenerationException, JsonMappingException, IOException {
        Event event = new Event("typeHere");
        String json = event.toJSON();
        
        ObjectMapper mapper = new ObjectMapper();
        Event parsedEvent = mapper.readValue(json, Event.class);
        
        assertEquals(event.getId(), parsedEvent.getId());
        // Test date matches but without millisecond accuracy
        assertEquals(event.getDate().getTime() / 1000, parsedEvent.getDate().getTime() / 1000);
        assertEquals(event.getData(), parsedEvent.getData());
        assertEquals(event.getType(), parsedEvent.getType());
    }

}
