package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> sessions = timetable.get(day);
        if (sessions == null) {
            sessions = new TreeMap<>();
            timetable.put(day, sessions);
        }

        List<TrainingSession> sessionsAtTime = sessions.get(time);
        if (sessionsAtTime == null) {
            sessionsAtTime = new ArrayList<>();
            sessions.put(time, sessionsAtTime);
        }

        sessionsAtTime.add(trainingSession);
    }


    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsForDay = timetable.get(dayOfWeek);//как реализовать, тоже непонятно, но сложность должна быть О(1)

        if (sessionsForDay == null) {
            return new ArrayList<>();
        }

        List<TrainingSession> result = new ArrayList<>();

        for (List<TrainingSession> sessions: sessionsForDay.values()) {
            result.addAll(sessions);
        }
        return result;
    }


    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        Map<TimeOfDay, List<TrainingSession>> sessionsForDay = timetable.get(dayOfWeek);//как реализовать, тоже непонятно, но сложность должна быть О(1)

        if (sessionsForDay == null) {
            return new ArrayList<>();
        }

        List<TrainingSession> result = sessionsForDay.get(timeOfDay);
        if (result == null) {
            return new ArrayList<>();
        } else {
            return result;

        }
    }


    public List<CounterOfTrainings> getCoachesByCoaches() {
        Map<Coach, Integer> counts = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> daySchedule : timetable.values()) {
            for (List<TrainingSession> sessions : daySchedule.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    counts.put(coach, counts.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : counts.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        Collections.sort(result);
        return result;
    }
}
