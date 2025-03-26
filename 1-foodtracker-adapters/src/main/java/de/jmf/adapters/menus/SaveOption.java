package de.jmf.adapters.menus;

public enum SaveOption implements MenuOption {
    BACK(0),
    ALL(1),
    USER(2),
    WEIGHT(3),
    MEALS(4);

    private final int value;

    SaveOption(int value) {
        this.value = value;
    }

    public int getCode() {
        return value;
    }

    public static SaveOption fromInt(int value) {
        return MenuOption.fromInt(SaveOption.class, value);
    }
}