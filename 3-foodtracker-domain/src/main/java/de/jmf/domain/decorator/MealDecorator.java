package de.jmf.domain.decorator;
import de.jmf.domain.entities.Meal;

public abstract class MealDecorator extends Meal {
    protected Meal decoratedMeal;

    public MealDecorator(Meal decoratedMeal) {
        super(decoratedMeal.getName(), decoratedMeal.getProtein(), decoratedMeal.getCalories());
        this.decoratedMeal = decoratedMeal;
    }

    @Override
    public String getName() {
        return decoratedMeal.getName();
    }

    @Override
    public int getCalories() {
        return decoratedMeal.getCalories();
    }

    @Override
    public int getProtein() {
        return decoratedMeal.getProtein();
    }

    public abstract int getFat();
    public abstract int getCarbs();
}