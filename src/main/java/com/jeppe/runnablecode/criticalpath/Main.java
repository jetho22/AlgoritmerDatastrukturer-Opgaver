package com.jeppe.runnablecode.criticalpath;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Main {

    /**
     *
     *
     *
     * HERUNDER ER MULIGE MANGLENDE METODER DER MÅSKE KAN BRUGES TIL EKSAMEN
     *
     *
     *
     */

    // Beregn total varighed for en bestemt event
    public static int totalDurationForEvent(List<Aktivitet> tabel, int event) {
        int total = 0;
        for (Aktivitet aktivitet : tabel) {
            if (aktivitet.getEvent() == event) {
                total += aktivitet.getDuration();
            }
        }
        return total;
    }

    // Find aktiviter for en given event
    public static List<Aktivitet> findActivitiesForEvent(List<Aktivitet> tabel, int event) {
        List<Aktivitet> activities = new ArrayList<>();
        for (Aktivitet aktivitet : tabel) {
            if (aktivitet.getEvent() == event) {
                activities.add(aktivitet);
            }
        }
        return activities;
    }

    // Find længste aktivitet
    public static Aktivitet findLongestActivity(List<Aktivitet> tabel) {
        Aktivitet longest = null;
        int maxDuration = 0;

        for (Aktivitet aktivitet : tabel) {
            if (aktivitet.getDuration() > maxDuration) {
                maxDuration = aktivitet.getDuration();
                longest = aktivitet;
            }
        }
        return longest;
    }

    // Find kritisk vej som liste
    public static List<Aktivitet> findCriticalPath(List<Aktivitet> tabel) {
        List<Aktivitet> criticalPath = new ArrayList<>();
        int currentEvent = 1;
        int index = 0;
        int maxDuration = 0;
        Aktivitet criticalTask = null;

        while (true) {
            while (index < tabel.size() && tabel.get(index).getEvent() == currentEvent) {
                if (tabel.get(index).getDuration() > maxDuration) {
                    maxDuration = tabel.get(index).getDuration();
                    criticalTask = tabel.get(index);
                }
                index++;
            }
            if (criticalTask != null) {
                criticalPath.add(criticalTask);
            }
            maxDuration = 0;
            criticalTask = null;

            if (index == tabel.size()) {
                break;
            }
            currentEvent = tabel.get(index).getEvent();
        }

        return criticalPath;
    }

    // Beregn gennemsnitlig varighed for alle aktiviter
    public static double calculateAverageDuration(List<Aktivitet> tabel) {
        int totalDuration = 0;
        for (Aktivitet aktivitet : tabel) {
            totalDuration += aktivitet.getDuration();
        }
        return (double) totalDuration / tabel.size();
    }


    // original klasse starter herunder
    public static void main(String[] args)
    {
        List <Aktivitet> tabel = new ArrayList<Aktivitet>();
        String aktivitetFile = "/Users/jeppethomsen/githubprojects/algodat/src/main/java/com/jeppe/runnablecode/criticalpath/data.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(aktivitetFile)))
        {
            String semiKolon = ";";
            String line = "";
            // Read each line from the file
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
                String[] data = line.split(semiKolon);
                Aktivitet aktivitet = new Aktivitet(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]));
                tabel.add(aktivitet);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        int totalDuration = 0;

        for (int i = 0; i < tabel.size(); i++)
            totalDuration += tabel.get(i).getDuration();

        System.out.println("Antal aktiviteter:      "+ tabel.size());
        System.out.println("Gennemsnitlig varighed: "+ (float) totalDuration / tabel.size());

        String kritiskVej = ("");
        int laengdeKritiskVej = 0;
        int noOfEvents = tabel.get(tabel.size()-1).getEvent();
        int aktuelEvent = 1;
        int indeks = 0;
        int maxVarighedAktuelEvent = 0;
        String maxTask = "";

        while (true)
        {
            while (indeks < tabel.size() && tabel.get(indeks).getEvent() == aktuelEvent)
            {
                if (maxVarighedAktuelEvent < tabel.get(indeks).getDuration())
                {
                    maxVarighedAktuelEvent = tabel.get(indeks).getDuration();
                    maxTask = tabel.get(indeks).getTask();
                }
                indeks++;

            }
            laengdeKritiskVej += maxVarighedAktuelEvent;
            kritiskVej += maxTask;
            maxVarighedAktuelEvent = 0;
            maxTask = "";
            if (indeks == tabel.size())
                break;
            aktuelEvent = tabel.get(indeks).getEvent();

        }

        System.out.println("Længde af kritisk vej:  "+laengdeKritiskVej);
        System.out.println("Kritisk vej:            "+kritiskVej);
    }
}