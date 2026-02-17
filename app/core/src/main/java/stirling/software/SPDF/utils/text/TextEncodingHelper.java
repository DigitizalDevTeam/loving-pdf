package stirling.software.SPDF.utils.text;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.font.PDFont;

public final class TextEncodingHelper {

    private TextEncodingHelper() {}

    public static boolean canEncodeCharacters(PDFont font, String text) {
        if (font == null || text == null) {
            return false;
        }

        try {
            for (int i = 0; i < text.length(); i++) {
                String ch = text.substring(i, i + 1);
                try {
                    byte[] enc = font.encode(ch);
                    if (enc == null || enc.length == 0) {
                        return false;
                    }
                } catch (IOException | RuntimeException ex) {
                    return false;
                }
            }
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean canCalculateBasicWidths(PDFont font) {
        if (font == null) {
            return false;
        }
        try {
            float avg = font.getAverageFontWidth();
            return avg > 0;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean hasCustomEncoding(PDFont font) {
        if (font == null) {
            return false;
        }
        try {
            java.lang.reflect.Method m = font.getClass().getMethod("getEncoding");
            Object enc = m.invoke(font);
            return enc != null;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (Throwable t) {
            return false;
        }
    }
}
