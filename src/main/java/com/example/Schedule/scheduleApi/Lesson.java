package com.example.Schedule.scheduleApi;

import com.example.Schedule.enums.OddOrEven;

public class Lesson {
    private String name; // название
    private String timeStart; // время начала занятия
    private String timeEnd; // время конца занятия
    private OddOrEven oddOrEven; // четное или нечетная неделя
    private int weekday;
    private String url;

    public Lesson(String name, String timeStart, String timeEnd, OddOrEven oddOrEven, int weekday, String url) {
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.oddOrEven = oddOrEven;
        this.weekday = weekday;
        this.url = url;
    }
    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OddOrEven getOddOrEven() {
        return oddOrEven;
    }

    public int getWeekday() {
        return weekday;
    }


}