package thepiedpiper.com.foodproject.logic.read.filter;

import java.util.List;

public interface Filter<E, T> {
    E searchOfName(List<E> values, String name);

    T max(E value);

    T max(E value, int year);

    T max(List<E> values);

    T max(List<E> values, int year);

    T max(List<E> values, String name);

    List<E> sorted(List<E> values, T name);

    List<E> sorted(List<E> values, T name, int year);
}
