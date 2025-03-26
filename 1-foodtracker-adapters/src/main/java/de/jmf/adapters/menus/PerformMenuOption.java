package de.jmf.adapters.menus;

import java.util.Map;

import de.jmf.adapters.helper.Strings;

public class PerformMenuOption {

    public <T> void execute(Map<T, Runnable> menuActions, T selected) {
        if (menuActions.containsKey(selected)) {
            menuActions.get(selected).run();
        } else {
            System.out.println(Strings.THE_INPUT_WAS_NOT_VALID_TRY_AGAIN);
        }
    }
}