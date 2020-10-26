package seedu.duke.model.foodoptions;

import java.util.ArrayList;

public class FoodPlacesData {

    private final String[][] foodPlacesScience = {
            {"Starbucks", "S9", "Mon-Fri: 7.30am to 9.00pm Sat – Sun: Closed"},
            {"Platypus Food Bar", "Science Block S16", "Mon-Fri, 8.30am-8.00pm"},
            {"Arise & Shine", "Science Block S16", "Mon-Fri, 7.00am-8.00pm Sat/Sun/PH, 7.00am-3.00pm"},
            {"Taiwan Ichiban", "Frontier", "Mon-Fri, 7.00am-7.00pm Sat, 9.00am-3.00pm Sun/PH closed"},
            {"Pasta Express", "Frontier", "Mon-Fri, 7.00am-7.00pm Sat, 9.00am-3.00pm Sun/PH closed"},
            {"Li Ji Coffeehouse", "Frontier", "Mon-Fri, 7.00am-7.00pm Sat, 9.00am-3.00pm Sun/PH closed"},
            {"Uncle Penyet Fusion - Halal specified", "Frontier", "Mon-Fri, 7.00am-7.00pm Sat, "
                    + "9.00am-3.00pm Sun/PH closed"},
            {"Cheers", "Frontier", "Mon-Fri, 7.00am-7.00pm Sat, 9.00am-3.00pm Sun/PH closed"},
            {"Jewel Coffee", "Medicine Block MD11", "Mon-Fri, 7.30am-10.00pm Sat/Sun/PH, 8.00am-5.00pm"},
            {"Eureka Taste", "Medicine Block MD6", "Mon-Fri: 7.00am-7.00pm"},
            {"Spinelli", "UHall Tan Chin Tuan Wing 1st Level", "Mon-Fri, 8.00am-6.30pm"}
    };

    private final String[][] foodPlacesPgp = {
            {"Foodclique", "PGPR", "Mon-Sun, 7.00am-9.30pm"},
            {"Supersnacks", "PGPR", "Mon-Fri, 12.00pm-1.00am"},
            {"Pines Aircon Food Court", "PGPR", "Mon-Fri, 7.00am-8.30pm Sat, 8.00am-8.30pm Sun/PH, 8.00am-8.00pm"},
            {"Nami", "innovation4.0", "Mon-Fri, 8.00am-5.30pm"}
    };

    private final String[][] foodPlacesComp = {
            {"The Spread", "Business School Mochtar Riady Building", "Mon-Fri, 7.45am-8.45pm Sat, 8.00am-2.30pm"},
            {"Salad Express X Pasta Express", "The Deck level 2", "Mon-Fri, 8.00am-8.00pm Sat, 8.00am – 3.00pm"},
            {"Uncle Penyet (Indonesian Express)", "The Deck level 2", "Mon-Fri, 10.30am-7.30pm Sat, 10.30am-3.00pm"},
            {"Liang Ban Kung Fu", "The Deck level 2", "Mon-Sat, 8.00am-8.00pm"},
            {"The Deck", "FASS", "Mon-Fri, 7.30am-4.00pm/8.00pm Sat,7.30am-3.00pm"},
            {"The Coffee Hut", "Arts Block AS2 (Beside LT13)", "Mon-Fri, 8.00am-6.00pm"},
            {"Omo store", "COM1", "24/7"},
            {"Cool Spot Drinks", "COM2", "24/7"},
            {"Takeaway Food Kiosks", "COM2", "24/7"},
            {"Cafe Delight", "Ventus", "Mon-Fri, 8.00am-6.00pm"},
            {"Reedz Café", "SFAH", "Mon-Fri, 8.30am-5.30pm Sat/Sun, 8.00am-3.00pm"},
            {"University Club", "SFAH", "Mon-Fri, 11.30am-2.30pm (Last seating 2pm, Last order 2.10pm) "
                    + "6.30pm-10.00pm (Last seating 9.45pm, Last order 10pm) Lounge Area: 11.30am to 11pm "
                    + "(Last seating 9.45pm, Last order 10pm)"},
            {"The Coffee Roaster", "Blk AS8", "Mon-Fri, 7.30am-7.00pm Sat, 9.00am-5.00pm"},
            {"Maxx Coffee", "Central Library", "Daily, 8.00am-10.00pm"}
    };

    private final String[][] foodPlacesEngin = {
            {"Central Square", "Yusof Ishak House Level 2", "Mon-Fri, 8.00am-8.00pm Sat, 8.00am-3.00pm"},
            {"Crave", "YIH", "Mon-Fri, 9.00am-8.00pm Sat, 9.00am-6.00pm"},
            {"Subway - Halal Certified", "YIH", "Mon-Sun, 10.00am-10.00pm"},
            {"Old Chang Kee - Halal Certified", "YIH", "Mon-Fri, 7.30am-9.00pm Sat, 7.30am-4.00pm"},
            {"Goh Bros", "YIH", "Mon-Fri, 7.30am-9.00pm Sat, 7.30am-4.00pm"},
            {"Platypus Food Bar", "Design & Environment Block SDE4", "Mon-Fri, 8.30am-8.00pm"},
            {"Platypus Food Bar", "Engineering Block E2A", "Mon-Fri, 9.00am-7.30pm"},
            {"E2 Halal Cafeteria - Halal Certified", "Engineering Block E2", "Mon-Fri, 7.00am-7.00pm Sat, "
                    + "08.00-am-1.00pm"},
            {"Cheers", "Engineering Block E3", "24/7"},
            {"Spinelli Coffee Company", "Engineering Block EA", "Mon-Fri, 8.00am-6.30pm"},
            {"Subway Mobile Cart - Halal Certified", "Engineering Block E4", "Mon-Fri, 11.00am-6.00pm"},
            {"Arise & Shine", "Engineering Block E4", "Mon-Fri, 7.00am-8.00pm Sat/Sun/PH, 7.00am-3.00pm"},
            {"Takeaway Food Kiosks", "Engineering Block E4", "Mon-Fri, 7.00am-8.00pm Sat/Sun/PH, 7.00am-3.00pm"},
            {"The Tea Party", "University Sports Centre", "Mon-Sat, 10.30am-10.30pm"}
    };

    private final String[][] foodPlacesMuseum = {
            {"Atempo", "Yong Siew Toh Conservatory of Music (YSTCM)", "Mon-Fri, 8.30am-5.00pm"},
            {"D’Arts @ UCC", "University Cultural Centre", "Mon-Fri and event days, 8.30am-5.00pm"},
            {"Bar Bar Black Sheep", "Alice Lee Plaza", "Mon-Sun, 12.00pm-12.00am"}
    };

    private final String[][] foodPlacesUtown = {
            {"Flavours@Utown", "Stephen Riady Centre", "Mon-Sun: 24 hours"},
            {"Waa Cow", "Stephen Riady Centre", "Mon-Thu, 11.30am-7.30pm Fri, 11.30am-9.30pm Sat, 12.00pm-3.00pm"},
            {"Super Snacks", "Stephen Riady Centre", "Daily, 11.00am-2.00am"},
            {"2359 Li Ji Coffeehouse", "Stephen Riady Centre", "Mon-Fri, 11.00am-1.00am Sat/Sun/PH, 11.00am-10.00pm"},
            {"Octobox", "Stephen Riady Centre", "24/7"},
            {"Fairprice Xpress", "Stephen Riady Centre", "24/7"},
            {"Fine Food", "Town Plaza", "Mon-Sun, 7.00am-10.00pm"},
            {"The Royals Bistro - Halal Certified", "Town Plaza", "Mon-Sat, 11.00am-8.30pm"},
            {"Sapore Italiano", "Town Plaza", "Mon-Sun, 11.00am-10.00pm"},
            {"Hwang's", "Town Plaza", "Mon-Sat, 10.00am-10.00pm"},
            {"Subway - Halal Certified", "Town Plaza", "Mon-Sat, 10.00am-10.00pm Sun, 10.00am-9.00pm"},
            {"Udon Food Bar", "Town Plaza", "Mon-Sat, 11.00am-10.00pm"},
            {"Cheers", "Town Plaza", "Mon-Sat, 11.00am-10.00pm"},
            {"Starbucks", "Education Resource Centre", "24/7"},
            {"Agora Cafe", "Yale-NUS East Core", "Mon-Sun, 8.30am-9.00pm"}
    };

    private final String[][] foodPlacesBtc = {
            {"The Summit - Halal Certified", "BTC Block B", "Mon-Fri, 7.00am-8.00pm Sat, 7.00am-4.00pm"},
            {"Octobox", "BTC Block B", "Mon-Fri, 7.00am-8.00pm Sat, 7.00am-4.00pm"},
            {"Reedz Cafe", "The Thinking Corner @ BTC Li Ka Shing Building", "Mon-Fri, 8.00am-7.00pm"}
    };

    private static ArrayList<FoodPlace> diningOptionsInNus = new ArrayList<>();

    public FoodPlacesData() {
        diningOptionsInNus.add(new FoodPlace(new String[]{"Science", "University Hall",
            "Medicine"}, foodPlacesScience));
        diningOptionsInNus.add(new FoodPlace(new String[]{"PGP"}, foodPlacesPgp));
        diningOptionsInNus.add(new FoodPlace(new String[]{"School of Business", "School of computing",
            "FASS", "Ventus", "Shaw Foundation"}, foodPlacesComp));
        diningOptionsInNus.add(new FoodPlace(new String[]{"Faculty of Engineering", "YIH", "USC"}, foodPlacesEngin));
        diningOptionsInNus.add(new FoodPlace(new String[]{"Museum"}, foodPlacesMuseum));
        diningOptionsInNus.add(new FoodPlace(new String[]{"University Town", "Yale NUS"}, foodPlacesUtown));
        diningOptionsInNus.add(new FoodPlace(new String[]{"BTC"}, foodPlacesBtc));
    }

    public static ArrayList<FoodPlace> getDiningOptionsInNus() {
        if (diningOptionsInNus.isEmpty()) {
            new FoodPlacesData();
        }
        return diningOptionsInNus;
    }
}
