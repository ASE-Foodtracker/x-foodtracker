package de.jmf.domain.entities;
import java.time.LocalDate;

import de.jmf.domain.valueobjects.Exercise;

public class ExerciseLog {
    private int id;
    private LocalDate date;
    private Exercise exercise;

    public ExerciseLog(int id, LocalDate date, Exercise exercise) {
        this.id = id;
        this.date = date;
        this.exercise = exercise;
    }

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