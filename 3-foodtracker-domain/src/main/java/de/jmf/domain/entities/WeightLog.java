package de.jmf.domain.entities;

import java.time.LocalDate;

import de.jmf.domain.valueobjects.Weight;

public class WeightLog {
    private LocalDate date;
    private Weight weight;

    public WeightLog(LocalDate date, Weight weight) {
        this.date = date;
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public Weight getWeight() {
        return weight;
    }
}
