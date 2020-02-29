package br.com.infox.displaykey;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Maicon
 */
public class DisplayKey {
    private static final String PT_URL = "/translations/pt.properties";

    private static String url;
    private static Properties properties;

    private DisplayKey() {
        throw new IllegalStateException("Utility class");
    }

    private static void setUrl(String idioma) {
        switch (idioma) {
            case "pt":
                url = PT_URL;
                break;
            default:
                url = PT_URL;
        }
    }

    private static void loadProperties() {
        try {
            properties.load(DisplayKey.class.getResourceAsStream(url));
        } catch (IOException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public static void setLocale(String locale) {
        properties = new Properties();
        setUrl(locale);
        loadProperties();
    }

    public static String get(String key, Object... arguments) {
        return MessageFormat.format(properties.getProperty(key), arguments);
    }
}
