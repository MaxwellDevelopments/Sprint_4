package ru.qa_scooter.constants;

// Для цветов самоката
public enum Color {
    BLACK ("чёрный жемчуг"),
    GREY ("серая безысходность");

    private final String stringColor;

    Color(String stringColor) {
        this.stringColor = stringColor;
    }

    public String getStringValue() {
        return stringColor;
    }

    @Override
    public String toString() {
        return "Color{" +
                "title='" + stringColor + '\'' +
                '}';
    }
}

