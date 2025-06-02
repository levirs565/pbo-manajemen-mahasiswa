package id.alfonlevi.mahasiswa.controller;

public class Utils {
    private Utils() {
    }

    public static class ControllerException extends Exception {
        public ControllerException(String message) {
            super(message);
        }
    }

    public static void ensureNotBlank(String nama, Object value) throws ControllerException {
        if ((value == null) || (value instanceof String && ((String) value).isBlank())) {
            throw new ControllerException(String.format("%s tidak boleh kosong", nama));
        }
    }
}
