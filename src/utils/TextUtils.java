package utils;

import java.text.Normalizer;

public class TextUtils {
    // Normalise le texte en retirant accents, casse et espaces superflus
    public static String normalize(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "")
            .replaceAll("[^\\p{ASCII}]", "")
            .toLowerCase()
            .trim();
        return normalized.replaceAll("\\s+", " ");
    }
}