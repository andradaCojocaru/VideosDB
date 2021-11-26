package main;

import fileio.*;

import java.util.List;

public class MyInput {
    List<ActorInputData> actorsData;
    List<MyUser> usersData;
    List<ActionInputData> commandsData;
    List<MyMovie> moviesData;
    List<MySerial> serialsData;

    public MyInput() {
        this.actorsData = null;
        this.usersData = null;
        this.commandsData = null;
        this.moviesData = null;
        this.serialsData = null;
    }

    public MyInput(List<ActorInputData> actors, List<MyUser> users,
                 List<ActionInputData> commands, List<MyMovie> movies,
                 List<MySerial> serials) {
        this.actorsData = actors;
        this.usersData = users;
        this.commandsData = commands;
        this.moviesData = movies;
        this.serialsData = serials;
    }

    public List<ActorInputData> getActors() {
        return actorsData;
    }

    public List<MyUser> getUsers() {
        return usersData;
    }

    public List<ActionInputData> getCommands() {
        return commandsData;
    }

    public List<MyMovie> getMovies() {
        return moviesData;
    }

    public List<MySerial> getSerials() {
        return serialsData;
    }
}
