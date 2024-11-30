package com.example.elec1compilation;

public class ExerciseItem {
    private String title;
    private String category;
    private Class<?> activityClass;

    public ExerciseItem(String title, String category, Class<?> activityClass) {
        this.title = title;
        this.category = category;
        this.activityClass = activityClass;
    }

    // Getters
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public Class<?> getActivityClass() { return activityClass; }
}
