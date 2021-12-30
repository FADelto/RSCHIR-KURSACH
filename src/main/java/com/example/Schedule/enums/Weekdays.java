package com.example.Schedule.enums;

public enum Weekdays {
    MONDAY(1,"Понедельник"),
    TUESDAY(2, "Вторник"),
    WEDNESDAY(3, "Среда"),
    THURSDAY(4, "Четверг"),
    FRIDAY(5, "Пятница"),
    SATURDAY(6, "Суббота"),
    SUNDAY(7, "Воскресенье");

    private final int day;
    private final String name;

    Weekdays(int day,String name) {
        this.day = day;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDayNumber() {
        return day;
    }

    public String getStringDay(int day){
        return switch (day) {
            case (1) ->  Weekdays.MONDAY.getName();
            case (2) ->  Weekdays.TUESDAY.getName();
            case (3) ->  Weekdays.WEDNESDAY.getName();
            case (4) ->  Weekdays.THURSDAY.getName();
            case (5) ->  Weekdays.FRIDAY.getName();
            case (6) ->  Weekdays.SATURDAY.getName();
            case (7) ->  Weekdays.SUNDAY.getName();
            default -> "ошибка";
        };
    }
}
