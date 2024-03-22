package ru.qa_scooter.constants;

// Злесь хранятся линки и названия страниц
public enum Page {
    DZEN_YANDEX ("https://dzen.ru", "Дзен"),
    HOME_PAGE_SCOOTER("https://qa-scooter.praktikum-services.ru", "undefined");

    private final String link;
    private final String title;

    Page(String link, String title) {
        this.link = link;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }
}
