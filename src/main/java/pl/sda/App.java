package pl.sda;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.sda.checker.Checker;
import pl.sda.checker.NumberChecker;
import pl.sda.converters.Converter;
import pl.sda.converters.GsonProvider;

import pl.sda.converters.JsonConverter;
import pl.sda.input.*;
import pl.sda.model.Game;
import pl.sda.model.GameType;
import pl.sda.model.Games;
import pl.sda.model.Order;
import pl.sda.network.GameApi;
import pl.sda.network.LottoGameApi;
import pl.sda.network.OkHttp;
import pl.sda.network.RequestBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {

        MyScanner scanner= new MyScanner();

        Input userInput = new UserInput(scanner, new DefaultTypeProvider(scanner));
        InputResult inputResult = userInput.getInputResults();

        LocalDateTime searchDate = LocalDateTime.of(2019, Month.JANUARY, 5, 0, 0);

        GameApi api = new LottoGameApi();
        Game game = api.getGameForDate(GameType.LOTTO, searchDate);

        Checker numberChecker = new NumberChecker();
        numberChecker.check(inputResult.getNumbers(), game.getNumbers());

        System.out.println(game);


    }

}
