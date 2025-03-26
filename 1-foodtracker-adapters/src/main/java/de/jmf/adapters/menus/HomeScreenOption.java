package de.jmf.adapters.menus;

public enum HomeScreenOption{
    LOGIN(1), 
    REGISTER(2);

    private final int code;

    HomeScreenOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static HomeScreenOption fromInt(int value) {
        for (HomeScreenOption option : HomeScreenOption.values()) {
            if (option.getCode() == value) {
                return option;
            }
        }
        return null;
    }
}