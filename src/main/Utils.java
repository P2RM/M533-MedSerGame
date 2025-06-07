package main;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils {
    public static String normalizeString(String input) {
        if (input == null) return "";
        String norm = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Enlève tous les accents
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        norm = pattern.matcher(norm).replaceAll("");
        // Espaces multiples → 1, tout en minuscules
        return norm.replaceAll("\\s+", " ").trim().toLowerCase();
    }
}