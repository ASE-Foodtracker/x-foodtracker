package de.jmf.adapters.helper;

import java.util.function.Supplier;

public class LoopUntilSuccessful{

    public void execute(Supplier<Boolean> function){
        boolean successful = false;
        while(!successful){
            successful = function.get();
        }
        System.out.println("Operation complete successfully");
    }
}