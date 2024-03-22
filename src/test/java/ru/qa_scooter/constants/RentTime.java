package ru.qa_scooter.constants;

// Для времени аренды
public enum RentTime {
    ONE ("сутки"),
    TWO ("двое суток"),
    THREE ("трое суток"),
    FOUR ("четверо суток"),
    FIVE ("пятеро суток"),
    SIX ("шестеро суток"),
    SEVEN ("семеро суток");

    private final String stringValue;

    RentTime(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        return "RentalPeriod{" +
                "title='" + stringValue + '\'' +
                '}';
    }
}
