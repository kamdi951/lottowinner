package pl.sda;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.sda.model.GameType;
import pl.sda.model.Games;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class App {

    public static void main(String[] args) throws IOException, InterruptedIOException {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), formatter);
            }
        })
                .registerTypeAdapter(GameType.class, new JsonDeserializer<GameType>() {
                    @Override
                    public GameType deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)
                            throws JsonParseException {
                        return GameType.getTypeByName(json.getAsString());
                    }
                })
                .create();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.lotto.pl/api/lotteries/draw-results/by-gametype?game=Lotto&index=1&size=15&sort=drawDate&order=DESC")
                .build();

        Response response = client.newCall(request).execute();
        String stringresponse = (response.body().string());

        Games lottoGamesFromBackend = gson.fromJson(stringresponse, Games.class);
        System.out.println(lottoGamesFromBackend);

    }

}
