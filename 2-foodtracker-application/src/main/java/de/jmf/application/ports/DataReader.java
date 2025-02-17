package de.jmf.application.ports;

import java.util.List;

public interface DataReader {
    String read();
    List<String[]> readAll();
}
