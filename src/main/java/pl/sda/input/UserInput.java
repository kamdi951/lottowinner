package pl.sda.input;

import pl.sda.model.GameType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput implements Input {

    private final MyScanner scanner;
    private final GameTypeProvider gameTypeProvider;

    public UserInput(MyScanner scanner,GameTypeProvider gameTypeProvider) {
        this.scanner = scanner;
        this.gameTypeProvider = gameTypeProvider;
    }

    @Override
    public InputResult getInputResults() {

        GameType gameType = gameTypeProvider.getGameType();
        List<Integer> userNumbers = new ArrayList<>();

        do {
            System.out.println("podaj liczbe nr " + (userNumbers.size() + 1) + ": ");

            String numAsString = scanner.next();
            int userNumber = 0;

            try {
                userNumber = Integer.parseInt(numAsString);
            } catch (NumberFormatException e) {
                System.out.println("Musisz podac liczbe");
                continue;
            }

            if (userNumbers.contains(userNumber)) {
                System.out.println("Juz podales taka liczbe, podaj inna");
                continue;
            }

            if (userNumber > 0 && userNumber <= gameType.getRange()) {
                userNumbers.add(userNumber);
            } else {
                System.out.println("liczba: " + userNumber + "jest z poza zakresu 1-" + gameType.getRange());
            }

        } while (userNumbers.size() < gameType.getMax());

        return new InputResult(gameType, userNumbers);
    }


}
