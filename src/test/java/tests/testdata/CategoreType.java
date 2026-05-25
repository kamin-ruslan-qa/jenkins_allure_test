package tests.testdata;

public enum CategoreType {

    Автозапчасти("Автозапчасти"),
    Автоэлектроника("Автоэлектроника"),
    Инструмент("Инструмент");

public final String header;

    CategoreType(String header) {
        this.header = header;
    }
}
