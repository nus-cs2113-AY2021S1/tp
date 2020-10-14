package seedu.duke.human;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.watchlist.Watchlist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User extends Human {
    public Bookmark bookmark;

    protected Date birthdate;
    protected Gender gender;

    private Watchlist activeWatchlist;
    private ArrayList<Watchlist> watchlistList;

    private static final SimpleDateFormat DATE_MONTH_YEAR = new SimpleDateFormat("dd/MM/yyyy");

    public User(String name, String birthdate, String gender) throws ParseException, AniException {
        super(name);
        setBirthdate(birthdate);
        setGender(gender);
        bookmark = new Bookmark();
        watchlistList = new ArrayList<>();
    }

    public void setGender(String genderString) throws AniException {
        genderString = genderString.toLowerCase();

        switch (genderString) {
        case "male":
            gender = Gender.Male;
            break;
        case "female":
            gender = Gender.Female;
            break;
        case "other":
            gender = Gender.Other;
            break;
        default:
            throw new AniException("Unexpected gender: " + genderString);
        }
    }

    public void setBirthdate(String birthdateString) throws ParseException {
        birthdate = DATE_MONTH_YEAR.parse(birthdateString);
        assert birthdate != null;
    }

    public void setActiveWatchlist(Watchlist activeWatchlist) {
        this.activeWatchlist = activeWatchlist;
    }

    public void setWatchlistList(ArrayList<Watchlist> watchlistList) {
        this.watchlistList = watchlistList;
    }

    public String getDobString() {
        return DATE_MONTH_YEAR.format(birthdate);
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public Watchlist getActiveWatchlist() {
        return activeWatchlist;
    }

    public String getActiveWatchlistName() {
        return activeWatchlist.getName();
    }

    public ArrayList<Watchlist> getWatchlistList() {
        return watchlistList;
    }

    /**
     * Provides the name of the user with Japanese honorifics depending on his gender.
     *
     * @return name of user with honorifics.
     */
    public String getFancyName() {
        if (gender == Gender.Female) {
            return name + "-chan";
        } else {
            return name + "-san";
        }
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\nBirthdate: " + getDobString() + "\nGender: " + getGender();
    }
}
