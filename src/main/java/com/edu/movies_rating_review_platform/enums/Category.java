package com.edu.movies_rating_review_platform.enums;

public enum Category {
    ABS("absurdist"),
    ACT("action"),
    ADV("adventure"),
    COM("comedy"),
    CRI("crime"),
    DRA("drama"),
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
    WES("western");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
