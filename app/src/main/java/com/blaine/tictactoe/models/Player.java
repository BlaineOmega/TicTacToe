package com.blaine.tictactoe.models;

/**
 * Created by blaineanderson on 3/14/17.
 */

public class Player {
    String name;
    int id;
    int score;

    public Player(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
