package com.pavel.vovniuk.dishes.dto;

public class ProduktTitleDishName {
    private  String title;
    private String name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProduktTitleDishName(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
