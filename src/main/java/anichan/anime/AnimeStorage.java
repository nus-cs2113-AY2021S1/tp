package anichan.anime;

import anichan.exception.AniException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static anichan.logger.AniLogger.getAniLogger;

/**
 * Manages the storage of anime data.
 */
public class AnimeStorage {

    private static final String FILE_RESOURCE_ERROR = "File within resource stream could not be found!";
    private static final Logger LOGGER = getAniLogger(Anime.class.getName());

    /**
     * Read anime data from offline database.
     *
     * @return ArrayList of anime object
     * @throws AniException if have error reading file
     */
    public ArrayList<Anime> readAnimeDatabase() throws AniException {
        LOGGER.log(Level.INFO, "Retrieving information from DataSource.");
        ArrayList<Anime> animeDataList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            LOGGER.log(Level.INFO, "Currently extracting from /AniListData/AniList-Data" + i + ".json");
            String fileData = getDataFromJarFile("/AniListData/AniList-Data" + i + ".json");
            LOGGER.log(Level.INFO, "Extraction of /AniListData/AniList-Data" + i + ".json successful");
            LOGGER.log(Level.INFO, "Parsing Json data.");
            parseJson(animeDataList, fileData);
            LOGGER.log(Level.INFO, "Parse Successful.");
        }
        LOGGER.log(Level.INFO, "Retrieval and Parsing for anime object in DataSource Successful.");
        return animeDataList;
    }

    /**
     * Parse the json file into anime object.
     *
     * @param animeDataList the list to keep anime object data
     * @param fileData      the json filedata to be parse into anime object
     * @throws AniException if error parsing string into json object
     */
    private void parseJson(ArrayList<Anime> animeDataList, String fileData) throws AniException {
        JSONParser parser = new JSONParser();
        JSONArray jsonList = new JSONArray();
        try {
            jsonList = (JSONArray) parser.parse(fileData);

        } catch (ParseException e) {
            LOGGER.log(Level.WARNING, "Parsing file failed!");
            e.printStackTrace();

        }

        Iterator iterator = jsonList.iterator();
        JSONObject jsonObject = new JSONObject();
        while (iterator.hasNext()) {
            String animeName;
            int animeEpisode = 0;
            int animeRating = 0;
            int animeDuration = 0;

            //Advance the Iterator
            jsonObject = (JSONObject) iterator.next();
            jsonObject = (JSONObject) jsonObject.get("data");
            jsonObject = (JSONObject) jsonObject.get("Media");

            //Getting anime name
            JSONObject jsonTitle = (JSONObject) jsonObject.get("title");
            if (jsonTitle.get("english") == null) {
                animeName = (String) jsonTitle.get("romaji");
            } else {
                animeName = (String) jsonTitle.get("english");
            }
            assert animeName != null : "Anime Name should not be null.";
            //getting anime episode

            if (jsonObject.get("episodes") != null) {
                animeEpisode = (int) (long) jsonObject.get("episodes");
            }

            //getting start date
            JSONObject jsonDate = (JSONObject) jsonObject.get("startDate");
            String[] animeReleaseDate;
            animeReleaseDate = new String[]{String.valueOf((long) jsonDate.get("year")),
                    String.valueOf((long) jsonDate.get("month")), String.valueOf((long) jsonDate.get("day"))};
            assert animeReleaseDate != null : "Release date should not be null.";

            //getting rating
            if (jsonObject.get("averageScore") != null) {
                animeRating = (int) (long) jsonObject.get("averageScore");
            }

            //getting genre
            ArrayList<String> animeGenre;
            animeGenre = new ArrayList<>();
            JSONArray jsonGenre = (JSONArray) jsonObject.get("genres");
            for (Object genre : jsonGenre) {
                animeGenre.add((String) genre);
            }
            String[] animeGenreArray;
            animeGenreArray = new String[animeGenre.size()];
            animeGenreArray = animeGenre.toArray(animeGenreArray);
            assert animeGenreArray != null : "Genre should not be null.";

            //getting duration
            if (jsonObject.get("duration") != null) {
                animeDuration = (int) (long) jsonObject.get("duration");
            }

            Anime anime = new Anime(animeName, animeReleaseDate, animeRating, animeGenreArray, animeDuration,
                    animeEpisode);
            animeDataList.add(anime);
        }
    }

    /**
     * Read file from resource stream. (Files embedded within the program).
     *
     * @param filename name of the resource stream file
     * @return file read in string
     * @throws AniException if error reading resourse stream file
     */
    public String getDataFromJarFile(String filename) throws AniException {
        assert filename != null : "Filename should not be null.";
        try {
            InputStream inputStream = AnimeStorage.class.getResourceAsStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String fileLine = "";
            String fileData = "";

            while ((fileLine = bufferedReader.readLine()) != null) {
                fileData += fileLine;
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            return fileData;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, FILE_RESOURCE_ERROR);
            throw new AniException(FILE_RESOURCE_ERROR);
        }

    }
}
