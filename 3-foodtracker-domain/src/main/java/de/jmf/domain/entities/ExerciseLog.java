package de.jmf.domain.entities;
import de.jmf.domain.valueobjects.Exercise;

import java.time.LocalDate;

public class ExerciseLog {
    private int id;
    private LocalDate date;
    private Exercise exercise;

    public ExerciseLog(int id, LocalDate date, Exercise exercise) {
        this.id = id;
        this.date = date;
        this.exercise = exercise;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Exercise getExercise() {
        return exercise;
    }
}