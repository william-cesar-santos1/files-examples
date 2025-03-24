package br.ada.tech.desenvolva1321.persistence;

import java.util.stream.Stream;

public interface SearchByName<T> {

    Stream<T> search(String name);

}
