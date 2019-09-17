package com.edu.movies_rating_review_platform.enums;

public enum Category {
    ABS("absurdist"),
    ACT("action"),
    ADV("adventure"),
    COM("comedy"),
    CRI("crime"),
    DRA("drama"),
    FAM("family"),
    FAN("fantasy"),
    HIS("historical"),
    HIS_FI("historical fiction"),
    HOR("horror"),
    MAG_RE("magical realism"),
    MYS("mystery"),
    PAR_FI("paranoid fiction"),
    PHI("philosophical"),
    POL("political"),
    ROM("romance"),
    SAG("saga"),
    SAT("satire"),
    SCI_FI("science fiction"),
    SOC("social"),
    SPE("speculative"),
    THR("thriller"),
    URB("urban"),
    WES("western"),

    DEF("default");

    private String categoryForUser;

    Category(String categoryForUser) {
        this.categoryForUser = categoryForUser;
    }

    public String getCategoryForUser() {
        return categoryForUser;
    }

    public static Category fromString(String text) {
        for (Category b : Category.values()) {
            if (b.getCategoryForUser().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return Category.DEF;
    }
}
