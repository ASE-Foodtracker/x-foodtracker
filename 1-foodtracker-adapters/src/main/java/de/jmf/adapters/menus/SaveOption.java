package de.jmf.adapters.menus;

public enum SaveOption {
    BACK(0),
    ALL(1),
    USER(2),
    WEIGHT(3),
    MEALS(4);

    private final int value;

    SaveOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SaveOption fromInt(int value) {
        for (SaveOption option : SaveOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid option: " + value);
    }
}