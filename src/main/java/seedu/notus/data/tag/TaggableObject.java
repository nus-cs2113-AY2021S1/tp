package seedu.notus.data.tag;

import java.util.ArrayList;

//@@author Chongjx
/**
 * Represents a TaggableObject. Contains an arraylist of tags. To be inherited by Note and Event.
 */
public abstract class TaggableObject {
    protected ArrayList<Tag> tags;

    public TaggableObject() {
        this.tags = new ArrayList<>();
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getTagsName() {
        String tagsName = "";

        for (Tag t : tags) {
            tagsName = tagsName.concat(t.toString()).concat(" ");
        }
        return tagsName;
    }
}
