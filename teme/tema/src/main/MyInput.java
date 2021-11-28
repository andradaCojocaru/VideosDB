package main;

import fileio.ActionInputData;
import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.List;

public class MyInput {
    @lombok.Getter
    private List<MyActor> actorsData;
    @lombok.Getter
    private List<MyUser> usersData;
    @lombok.Getter
    private List<ActionInputData> commandsData;
    @lombok.Getter
    private List<MyMovie> moviesData;
    @lombok.Getter
    private List<MySerial> serialsData;

    /**
     * class for MyInput
     */
    public MyInput() {
        this.actorsData = null;
        this.usersData = null;
        this.commandsData = null;
        this.moviesData = null;
        this.serialsData = null;
    }

    /**
     * @param actors
     * @param users
     * @param commands
     * @param movies
     * @param serials
     */
    public MyInput(final List<MyActor> actors, final List<MyUser> users,
                 final List<ActionInputData> commands, final List<MyMovie> movies,
                 final List<MySerial> serials) {
        this.actorsData = actors;
        this.usersData = users;
        this.commandsData = commands;
        this.moviesData = movies;
        this.serialsData = serials;
    }
}

