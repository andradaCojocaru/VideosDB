package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import main.Commands.Command;
import main.Entities.MyUser;
import main.Get.GetFavorite;
import main.Get.GetViews;
import main.Queries.QueryActors;
import main.Queries.QueryUsers;
import main.Queries.QueryVideo;
import main.Recommendations.RecommendationArray;
import main.Recommendations.RecommendationOneResult;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH,
                Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();
        MyInputLoader myInputLoader = new MyInputLoader(input);
        MyInput myInput = new MyInput(myInputLoader.getActorsData(),
                myInputLoader.getUsersData(), myInputLoader.getCommandsData(),
                myInputLoader.getMoviesData(), myInputLoader.getSerialsData());

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        List<ActionInputData> commands = new ArrayList<>();
        String message = new String();
        commands = input.getCommands();
        GetViews getViews = new GetViews();
        getViews.numberViewsMovie(myInput.getUsersData(),
                myInput.getMoviesData());
        getViews.numberViewsSerial(myInput.getUsersData(),
                myInput.getSerialsData());
        GetFavorite getFavorite = new GetFavorite();
        getFavorite.numberFavoritesMovie(myInput.getUsersData(), myInput.getMoviesData());
        getFavorite.numberFavoritesSerial(myInput.getUsersData(), myInput.getSerialsData());
        for (ActionInputData command : commands) {
            String username = command.getUsername();
            MyUser newUser = new MyUser();
            for (MyUser user : myInput.getUsersData()) {
                if (user.getUsername().equals(username)) {
                    newUser = user;
                    break;
                }
            }
            String actionType = command.getActionType();
            if (actionType.equals("command")) {
                Command commandNew = new Command();
                message = commandNew.getCommand(command, newUser, myInput);
            } else if (actionType.equals("query")) {
                String action = command.getObjectType();
                String criteria = command.getCriteria();
                if (action.equals("actors")) {
                    QueryActors actors = new QueryActors();
                    message = actors.getQueryActors(criteria, myInput, command);
                } else if (action.equals("shows") || action.equals("movies")) {
                    QueryVideo queryVideo = new QueryVideo();
                    message = queryVideo.getQueryVideo(command, action, myInput, criteria);
                } else if (action.equals("users")) {
                    QueryUsers queryUsers = new QueryUsers();
                    message = queryUsers.getQueryusers(myInput, command);
                }
            } else if (actionType.equals("recommendation")) {
                String action = new String();
                action = command.getType();
                if (action.equals("standard") || action.equals("best_unseen")
                        || action.equals("popular") || action.equals("favorite")) {
                    RecommendationOneResult recommendationOneResult = new RecommendationOneResult();
                    message = recommendationOneResult.getRecommendationOneResult(myInput,
                            action, newUser);
                } else if (action.equals("search")) {
                    RecommendationArray recommendationArray = new RecommendationArray();
                    message = recommendationArray.getRecommendationArray(newUser, command, myInput);
                }
            }
            arrayResult.add(fileWriter.writeFile(command.getActionId(),
                    message));
        }

        fileWriter.closeJSON(arrayResult);
    }
}
