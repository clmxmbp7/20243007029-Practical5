package com.example.practicalfive;

import java.util.Locale;

/** Validates a municipality entered by the user and returns its display name. */
public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String cleanedMunicipality = municipality.trim();
        if (cleanedMunicipality.isEmpty()) {
            return null;
        }

        switch (cleanedMunicipality.toLowerCase(Locale.ROOT)) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "chongqing":
                return "Chongqing";
            case "tianjin":
                return "Tianjin";
            default:
                return null;
        }
    }
}

