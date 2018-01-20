package sg.edu.nus.iss.ft08.phoenix;

import android.net.Uri;

public class Urls {

    static final String serverIpAddress = "192.168.206.129";
    static final String serverPort = "8080";


    private static Uri.Builder rootUrl(){
        String root = "http://" + serverIpAddress + ":" + serverPort + "/phoenix/api";

        return Uri
            .parse(root)
            .buildUpon();
    }

    public static String forAccount(){
        return rootUrl()
            .appendEncodedPath("account")
            .build()
            .toString();
//        return "http://" + serverIpAddress + ":" + serverPort + "/phoenix/api/account";
    }

    public static String forRadioPrograms() {
        return rootUrl()
            .appendEncodedPath("radioprograms")
            .build()
            .toString();
    }

    public static String forRadioProgram() {
        return rootUrl()
            .appendEncodedPath("radioprogram")
            .build()
            .toString();
    }

    public static String forUsers() {
        return rootUrl()
                .appendEncodedPath("users")
                .build()
                .toString();
    }

    public static String forUser(){
        return rootUrl()
                .appendEncodedPath("user")
                .build()
                .toString();
    }

    public static String forSchedules() {
        return rootUrl()
                .appendEncodedPath("schedules")
                .build()
                .toString();
    }

    public static String forSchedulesWeek(Integer weekNumber) {
        return rootUrl()
            .appendEncodedPath("schedules")
            .appendEncodedPath("week")
            .appendEncodedPath(weekNumber.toString())
            .build()
            .toString();
    }

    public static String forSchedulesRadioProgram(int programId) {
        return rootUrl()
            .appendEncodedPath("schedules")
            .appendEncodedPath("radioprogram")
            .appendEncodedPath(Integer.toString(programId))
            .build()
            .toString();
    }

    public static String forSchedulesUser(String userId) {
        return rootUrl()
            .appendEncodedPath("schedules")
            .appendEncodedPath("user")
            .appendEncodedPath(userId)
            .build()
            .toString();
    }

    public static String forCopyWeekSchedules(int sourceWeekNumber, int targetWeekNumber) {
        return rootUrl()
            .appendEncodedPath("schedules")
            .appendEncodedPath("copy")
            .appendEncodedPath("week")
            .appendEncodedPath(Integer.toString(sourceWeekNumber))
            .appendEncodedPath(Integer.toString(targetWeekNumber))
            .build()
            .toString();
    }

    public static String forSchedule() {
        return rootUrl()
                .appendEncodedPath("schedule")
                .build()
                .toString();
    }

    public static String forRadioProgram(int programId) {
        return rootUrl()
                .appendEncodedPath("radioprogram")
                .appendEncodedPath(Integer.toString(programId))
                .build()
                .toString();
    }
    public static String forSchedule(int scheduleId) {
        return rootUrl()
                .appendEncodedPath("schedule")
                .appendEncodedPath(Integer.toString(scheduleId))
                .build()
                .toString();
    }

    public static String forUser(String userId){
        return rootUrl()
                .appendEncodedPath("user")
                .appendEncodedPath(userId)
                .build()
                .toString();
    }

    public static String forSummary() {
        return rootUrl()
            .appendEncodedPath("summary")
            .build()
            .toString();
    }
    public static String forOverlap() {
        return rootUrl()
                .appendEncodedPath("schedule")
                .appendEncodedPath("overlap")
                .build()
                .toString();
    }
    public static String forPresenters() {
        return rootUrl()
                .appendEncodedPath("presenters")
                .build()
                .toString();
    }
    public static String forProducers() {
        return rootUrl()
                .appendEncodedPath("producers")
                .build()
                .toString();
    }

    public static String forassignedUser(String userId){
        return rootUrl()
            .appendEncodedPath("user")
            .appendEncodedPath("assigneduser")
            .appendEncodedPath(userId)
            .build()
            .toString();
    }


}
