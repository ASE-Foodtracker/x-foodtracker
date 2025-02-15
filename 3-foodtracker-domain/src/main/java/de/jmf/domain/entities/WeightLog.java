package de.jmf.domain.entities;

import java.time.LocalDate;

public class WeightLog {
    private int id;
    private LocalDate date;
    private double weight;

    public WeightLog(int id, LocalDate date, double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }
}