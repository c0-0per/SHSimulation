package pet;

import java.util.logging.Logger;

/**
 * Represents a pet with basic attributes like nickname, age, gender, and type.
 * This class is used to model a pet's basic characteristics and behaviors.
 * It includes functionalities such as logging the pet's actions.
 */
public class Pet {
    private static final Logger LOGGER = Logger.getLogger(Pet.class.getName());

    private String nickname;
    private int age;
    private String gender;
    private PetType petType;

    /**
     * Constructs a new Pet with the given nickname, age, gender, and type.
     *
     * @param nickname the nickname of the pet
     * @param age      the age of the pet
     * @param gender   the gender of the pet
     * @param petType  the type of the pet (e.g., dog, cat)
     */
    public Pet(String nickname, int age, String gender, PetType petType) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.petType = petType;
    }

    public String getNickname() {
        return nickname;
    }

    public void say(String word) {
        LOGGER.info(word);
    }

}
