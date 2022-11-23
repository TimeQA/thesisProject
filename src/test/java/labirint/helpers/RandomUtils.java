package labirint.helpers;

import com.github.javafaker.Faker;

public class RandomUtils {

    private static final Faker FAKER = new Faker();

    public static String getRandomSurname() {
        return FAKER.name().lastName();
    }

    public static String getRandomName() {
        return FAKER.name().firstName();
    }

    public static String getRandomSecondName() {
        return FAKER.name().lastName();
    }
}