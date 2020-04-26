package br.com.vinirib.pact.consumer.client.config;


import com.fasterxml.jackson.databind.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.zalando.gson.money.MoneyTypeAdapterFactory;
import org.zalando.jackson.datatype.money.MoneyModule;

import java.lang.reflect.Modifier;


@Configuration
public class ClientBeans {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .registerTypeAdapterFactory(new MoneyTypeAdapterFactory())
                .serializeNulls()
                .create();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Module moneyModule() {
        return new MoneyModule()
                .withMoney()
                .withDefaultFormatting();
    }
}
