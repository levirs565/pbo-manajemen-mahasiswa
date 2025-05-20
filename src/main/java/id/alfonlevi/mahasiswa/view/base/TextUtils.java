package id.alfonlevi.mahasiswa.view.base;

import org.apache.commons.text.StringEscapeUtils;

public class TextUtils {
    private TextUtils() {}

    public static String intoHtml(String text) {
        return String.format("<html>%s</html>", StringEscapeUtils.escapeHtml4(text));
    }
}
