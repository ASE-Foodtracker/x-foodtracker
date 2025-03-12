package de.jmf.adapters.menus;

import java.util.Map;

import de.jmf.adapters.helper.strings;

public class PerformMenuOption {

    public <T> void execute(Map<T, Runnable> mainMenuActions, T selected) {
        if (mainMenuActions.containsKey(selected)) {
            mainMenuActions.get(selected).run();
        } else {
            System.out.println(strings.THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
        }
    }
}