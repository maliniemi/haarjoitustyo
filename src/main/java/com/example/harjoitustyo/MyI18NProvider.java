package com.example.harjoitustyo;

import com.vaadin.flow.i18n.I18NProvider;
import java.text.MessageFormat;
import java.util.*;

public class MyI18NProvider implements I18NProvider {

    public static final String BUNDLE_PREFIX = "i18n/messages";

    @Override
    public List<Locale> getProvidedLocales() {
        return List.of(Locale.ENGLISH, new Locale("fi"));
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);
        String value = bundle.getString(key);
        if (params.length > 0) {
            return MessageFormat.format(value, params);
        }
        return value;
    }
}
