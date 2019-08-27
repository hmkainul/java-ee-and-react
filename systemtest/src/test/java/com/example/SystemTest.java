package com.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
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
            .headers(headers())
            .get(JsonObject.class);
    }

    private MultivaluedMap<String, Object> headers() {
        MultivaluedMap<String, Object> m = new MultivaluedHashMap<>();
        JsonObject user = Json.createObjectBuilder()
            .add("username", "ALICE")
            .add("password", "qwerty")
            .build();
        JsonObject result = target
            .path("login")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.json(user), JsonObject.class);
        String token = result.getString("token");
        m.add("Authorization", "Bearer " + token);
        return m;
    }

}
