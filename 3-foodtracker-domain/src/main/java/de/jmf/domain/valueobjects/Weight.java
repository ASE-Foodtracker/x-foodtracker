package de.jmf.domain.valueobjects;

public class Weight {
    private final double value;

    public Weight(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weight)) return false;
        Weight weight = (Weight) o;
        return Double.compare(weight.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return value + " kg";
    }
}
