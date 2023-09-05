package ru.timeconqueror.molang.custom;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class QueryDomain {
    public static final Map<String, String> DOMAINS = new HashMap<>();

    public static void register(String property, String domain) {
        DOMAINS.put(property, domain);
    }

    @Nullable
    public static String getDomain(String property) {
        return DOMAINS.get(Aliases.resolve(property));
    }
}
