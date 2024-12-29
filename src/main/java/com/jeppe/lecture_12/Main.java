package com.jeppe.lecture_12;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        File file = getFileIfExists();

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] attributes = line.split(";");

                activities.add(new Activity(
                        Integer.parseInt(attributes[0]),
                        attributes[1],
                        Integer.parseInt(attributes[2])
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Amount of activities: " + activities.size());
        System.out.println("Average duration: " + getAverageDuration(activities));

        int minimumDuration = 0;
        List<String> orderOfTasks = new ArrayList<>();
        for (Map.Entry<Integer, Activity> set : getCriticalPath(activities).entrySet()) {
            minimumDuration += set.getValue().getDuration();
            orderOfTasks.add(set.getValue().getTask());
        }

        System.out.println("Minimum duration: " + minimumDuration);
        System.out.println("Order of tasks: " + orderOfTasks);


    }

    private static File getFileIfExists() {
        File file = new File("src/main/java/com/jonas/lecture_12/data.txt");

        if (file.exists()) {
            return file;
        } else {
            throw new RuntimeException();
        }
    }

    private static double getAverageDuration(List<Activity> activities) {
        double sum = 0;
        for (Activity activity : activities) {
            sum += activity.getDuration();
        }

        return sum / activities.size();
    }

    private static Map<Integer, Activity> getCriticalPath(List<Activity> activities) {
        Map<Integer, Activity> eventMap = new HashMap<>();

        for (Activity activity : activities) {
            int event = activity.getEvent();

            if (!eventMap.containsKey(event)) {
                eventMap.put(event, activity);
            } else {
                if (eventMap.get(event).getDuration() < activity.getDuration()) {
                    eventMap.replace(event, activity);
                }
            }
        }

        return eventMap;
    }
}
