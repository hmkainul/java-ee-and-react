package com.example;

import javax.json.bind.*;
import javax.ws.rs.ext.*;

@Provider
public class JsonConfigurator implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class<?> type) {
        JsonbConfig config = new JsonbConfig()
            .withFormatting(true);
        return JsonbBuilder
            .newBuilder()
            .withConfig(config)
            .build();
    }

}
