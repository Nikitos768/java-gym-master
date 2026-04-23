package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private final Coach coach;
    private final int count;

    public CounterOfTrainings(Coach coach, int count) {
        this.coach = coach;
        this.count = count;
    }

    @Override
    public int compareTo(CounterOfTrainings other) {
        return Integer.compare(other.count, this.count);
    }

    @Override
    public String toString() {
        return coach + ": " + count;
    }

}
