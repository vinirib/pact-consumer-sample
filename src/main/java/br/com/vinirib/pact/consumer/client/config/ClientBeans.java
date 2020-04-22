package br.com.vinirib.pact.consumer.client.config;


import com.fasterxml.jackson.databind.Module;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.zalando.jackson.datatype.money.MoneyModule;


@Configuration
public class ClientBeans {

    @Bean
    public Gson gson() {
        return new Gson();
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
