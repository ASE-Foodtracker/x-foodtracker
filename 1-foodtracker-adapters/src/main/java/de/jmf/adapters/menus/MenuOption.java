package de.jmf.adapters.menus;

public interface MenuOption {
    int getCode();
    
    static <T extends MenuOption> T fromInt(Class<T> enumType, int value) {
        for (T option : enumType.getEnumConstants()) {
            if (option.getCode() == value) {
                return option;
            }
        }
        return null;
    }
}