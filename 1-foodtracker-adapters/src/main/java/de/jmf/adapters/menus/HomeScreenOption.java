package de.jmf.adapters.menus;

public enum HomeScreenOption implements MenuOption{
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
        return MenuOption.fromInt(HomeScreenOption.class, value);
    }
}