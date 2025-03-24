package de.jmf.domain.decorator;
import de.jmf.domain.entities.Meal;


public class FatDecorator extends MealDecorator {
    private final int fat;

    public FatDecorator(Meal decoratedMeal, int fat) {
        super(decoratedMeal);
        this.fat = fat;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public int getCarbs() {
        return decoratedMeal instanceof MealDecorator ? ((MealDecorator) decoratedMeal).getCarbs() : 0;
    }
}