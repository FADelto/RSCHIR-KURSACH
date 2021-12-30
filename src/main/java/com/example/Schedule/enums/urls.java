package com.example.Schedule.enums;

public enum urls {
    LOGIN_PAGE("https://login.mirea.ru/login/"),
    MY_PAGE("https://online-edu.mirea.ru/my/"),
    LESSON_URL("https://online-edu.mirea.ru/mod/webinars/view.php?id="),
    JSON_URL("http://delto.xyz:5000/api/schedule/");

    private final String url;

    urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
