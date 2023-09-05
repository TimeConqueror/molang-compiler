package ru.timeconqueror.molang.custom;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class QueryDomain {
    public static final Map<String, String> DOMAINS = new HashMap<>();

    public static void register(String property, String domain) {
        if (DOMAINS.put(property, domain) != null) {
            throw new IllegalArgumentException("Property is already registered: %s".formatted(property));
        }
    }

    @Nullable
    public static String getDomain(String property) {
        return DOMAINS.get(Aliases.resolve(property));
    }
}
