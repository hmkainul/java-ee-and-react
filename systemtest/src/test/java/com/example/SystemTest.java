package com.example;

import javax.json.JsonObject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class SystemTest {

    private Client client;

    private WebTarget target;

    @Before
    public void before() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/backend/resources/");
    }

    @After
    public void after() {
        client.close();
    }

    @Test
    public void testHydrogen() {
        JsonObject h = get("elements/1");
        assertEquals(1, h.getInt("number"));
        assertEquals("H", h.getString("symbol"));
        assertEquals("Hydrogen", h.getString("name"));
        assertEquals("1.008", h.getString("weight"));
    }

    private JsonObject get(String path) {
        return target
            .path(path)
            .request(MediaType.APPLICATION_JSON)
            .get(JsonObject.class);
    }

}
