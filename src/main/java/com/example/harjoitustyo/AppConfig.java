package com.example.harjoitustyo;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public I18NProvider i18nProvider() {
        return new MyI18NProvider();
    }
}
