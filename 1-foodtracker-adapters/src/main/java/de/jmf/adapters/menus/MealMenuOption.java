package de.jmf.adapters.menus;

public enum MealMenuOption implements MenuOption {
    BACK(0), 
    ADD_MEAL(1), 
    REMOVE_MEAL(2), 
    SHOW_MEALS(3);

    private final int code;

    MealMenuOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MealMenuOption fromInt(int value) {
        return MenuOption.fromInt(MealMenuOption.class, value);
    }
}