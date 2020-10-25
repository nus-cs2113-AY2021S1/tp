package profile;

import static profile.Constants.PROFILE_STRING_REPRESENTATION;
import static profile.Utils.checkValidProfile;

/**
 * A class that manages user profile.
 */
public class Profile {

    protected String name;
    protected int age;
    protected int height;
    protected double weight;
    protected double expectedWeight;

    /**
     * Constructs Profile object.
     *
     * @param name User's name.
     * @param age User's age.
     * @param height User's height in centimeters.
     * @param weight User's weight in kilograms.
     * @param expectedWeight User's expected weight in kilograms.
     */
    public Profile(String name, int age, int height, double weight, double expectedWeight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.expectedWeight = expectedWeight;
    }

    /**
     * Overrides toString method of class Object to get string presentation of Profile object.
     *
     * @return String presentation of Profile object.
     */
    public String toString() {
        assert checkValidProfile(this) : "Profile is invalid";
        return String.format(PROFILE_STRING_REPRESENTATION, getName(), getAge(), getHeight(), getWeight(),
                getExpectedWeight(), getBmiClassification());
    }

    /**
     * Gets user's name.
     *
     * @return User's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets user's age.
     *
     * @return User's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets user's height.
     *
     * @return User's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets user's weight.
     *
     * @return User's weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets user's expected weight.
     *
     * @return User's expected weight.
     */
    public double getExpectedWeight() {
        return expectedWeight;
    }

    /**
     * Gets user's bmi index and classification.
     *
     * @return User's bmi index and classification.
     */
    public String getBmiClassification() {
        String classification;

        double bmiIndex = weight / Math.pow((double) height / 100, 2);

        if (bmiIndex < 18.5) {
            classification = "Underweight";
        } else if (bmiIndex <= 24.9) {
            classification = "Normal Weight";
        } else if (bmiIndex <= 29.9) {
            classification = "Overweight";
        } else if (bmiIndex <= 34.9) {
            classification = "Obesity Class 1";
        } else if (bmiIndex <= 39.9) {
            classification = "Obesity Class 2";
        } else {
            classification = "Extreme Obesity Class 3";
        }

        return String.format("%.1f (%s)", bmiIndex, classification);
    }

    /**
     * Overrides equals method of class Object to compare Profile object.
     *
     * @param o Object to compare.
     * @return True if this Profile object is the same as the obj
     *         argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return name.equals(profile.name)
                && age == profile.age
                && height == profile.height
                && Double.compare(profile.weight, weight) == 0
                && Double.compare(profile.expectedWeight, expectedWeight) == 0;
    }
}
