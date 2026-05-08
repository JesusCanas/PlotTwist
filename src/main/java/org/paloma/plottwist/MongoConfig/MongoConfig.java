package org.paloma.plottwist.MongoConfig;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        // Metemos la URI a fuego aquí. Si esto no conecta, es que el PC tiene bloqueada la salida a Atlas.
        return MongoClients.create("mongodb+srv://admin:Mario!64@servidorbasedatos.d7bzjyr.mongodb.net/?appName=servidorBaseDatos");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        // Esto le dice a Spring: "Usa este cliente y no el de localhost"
        return new MongoTemplate(mongoClient(), "test"); 
    }
}