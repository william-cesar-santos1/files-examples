package br.ada.tech.desenvolva1321.persistence;

import java.util.Optional;
import java.util.stream.Stream;

public interface Reader<T> {

    Optional<T> read(Long id);

    Stream<T> read();

}
