package com.pvxdv.metrics_producer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Kafka Example App",
                description = "Home Work #2. Inno.tech.ru course", version = "1.0.0",
                contact = @Contact(
                        name = "Ryzhikh Pavel",
                        email = "rizhikh.pavel@yandex.ru",
                        url = "https://t.me/PVxDV"
                )
        )
)
public class OpenApiConfig {
}
