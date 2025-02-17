package de.jmf.application.ports;

public interface DataWriter {
    boolean save();
    boolean saveAll(String input);
    boolean update();
    boolean delete();
}
