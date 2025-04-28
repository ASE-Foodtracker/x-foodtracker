package de.jmf.adapters.menus;

public enum MainMenuOption implements MenuOption {
    QUIT(0), 
    DETAILS(1), 
    GYMPLAN(2), 
    WEIGHT_ENTRY(3), 
    MEAL_MANAGEMENT(4), 
    SAVE(5), 
    LOGOUT(6);

    private final int code;

    MainMenuOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MainMenuOption fromInt(int value) {
        return MenuOption.fromInt(MainMenuOption.class, value);
    }
}
