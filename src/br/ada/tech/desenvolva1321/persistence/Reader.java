package br.ada.tech.desenvolva1321.persistence;

import java.util.List;
import java.util.Optional;

public interface Reader<T> {

    Optional<T> read(Long id);

    List<T> read();

}
