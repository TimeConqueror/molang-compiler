package ru.timeconqueror.molang.custom;

import java.util.Map;

public class Aliases {
    private static final Map<String, String> BEDROCK_ALIASES = Map.of(
            "q", "query",
            "c", "context",
            "g", "global",
            "v", "variable"
    );

    public static String resolve(String alias) {
        if(BEDROCK_ALIASES.containsKey(alias)) {
            return BEDROCK_ALIASES.get(alias);
        }

        return alias;
    }
}
