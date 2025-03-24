package de.jmf.domain.decorator;
import de.jmf.domain.entities.Meal;

public class CarbsDecorator extends MealDecorator {
    private final int carbs;

    public CarbsDecorator(Meal decoratedMeal, int carbs) {
        super(decoratedMeal);
        this.carbs = carbs;
    }

    @Override
    public int getCarbs() {
        return carbs;
    }

    @Override
    public int getFat() {
        return decoratedMeal instanceof MealDecorator ? ((MealDecorator) decoratedMeal).getFat() : 0;
    }
}