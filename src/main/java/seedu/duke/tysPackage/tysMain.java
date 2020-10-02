package seedu.duke.tysPackage;

import seedu.duke.tysPackage.Timetable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class tysMain {
    public static ArrayList<Timetable> timeTable1 = new ArrayList<>();

    public static void testShowFunction(){
        timeTable1.add(new Timetable(LocalTime.parse("08:30"),
                LocalTime.parse("10:30"),
                "Monday",
                "CS2113T Tutorial"));
        timeTable1.add(new Timetable(LocalTime.parse("15:00"),
                LocalTime.parse("16:00"),
                "Monday",
                "CS2113T Tutorial"));
        timeTable1.add(new Timetable(LocalTime.parse("10:00"),
                LocalTime.parse("12:00"),
                "Tuesday",
                "CG2023 Lecture"));
        timeTable1.add(new Timetable(LocalTime.parse("15:00"),
                LocalTime.parse("18:00"),
                "Tuesday",
                "CG2027 Lab"));

        String input;
        do{
            System.out.print("enter day: ");
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            showLessonAtTime(input);
        }while(input.equals("exit") == false);

        showLessonAtTime(null);

    }

    public static void showLessonAtTime(String dayInput){
        if(dayInput == null) {
            showAllLessons();
            return;
        }

        System.out.println("Lessons for " + dayInput);
        for(int i = 0; i < timeTable1.size(); i++){
            if(timeTable1.get(i).getDay().equals(dayInput)){
                System.out.println(timeTable1.get(i).toString());
            }
        }
    }

    public static void showAllLessons(){
        System.out.println("Lessons for the whole week");
        for(int i = 0; i < timeTable1.size(); i++){
            System.out.println(timeTable1.get(i).toString());
        }
    }

}
