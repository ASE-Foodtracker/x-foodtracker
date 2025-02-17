package de.jmf.application.ports;

public interface DataWriter {
    boolean save();
    boolean saveAll();
    boolean update();
    boolean delete();
}
