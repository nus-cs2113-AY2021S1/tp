package seedu.duke.data.notebook;

import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a TagManager. Manages the tags for the notes.
 */
public class TagManager {
    private static Map<Tag, ArrayList<Note>> tagMap;

    public TagManager() {
        tagMap = new HashMap<>();
    }

    /**
     * Checks if the Tag exists in the Map.
     *
     * @param tagName name of the Tag to check.
     * @return true if Tag exists, false otherwise.
     */
    private boolean containsTag(String tagName) {
        for(Tag t : tagMap.keySet()) {
            if(t.getTagName().equalsIgnoreCase(tagName)) {
                return true;
            }
        }
        return false;
    }

    public Tag getTag(String tagName) {
        for(Tag t : tagMap.keySet()) {
            if(t.getTagName().equalsIgnoreCase(tagName)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Creates a Tag with the provided name.
     *
     * @param tagName name of the Tag.
     */
    public void createTag(String tagName) {
        boolean isTagExist = containsTag(tagName);

        if(!isTagExist) {
            tagMap.put(new Tag(tagName), new ArrayList<>());
        }
    }

    /**
     * Creates a Tag with the provided name and color.
     *
     * @param tagName name of the Tag.
     * @param tagColor color of the Tag.
     */
    public boolean createTag(String tagName, String tagColor) {
        boolean isTagExist = containsTag(tagName);

        if(!isTagExist) {
            tagMap.put(new Tag(tagName, tagColor), new ArrayList<>());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tags a Note with the provided name. Creates a new Tag if the Tag does not exist.
     *
     * @param note Note to be tagged.
     * @param tagName name of the Tag.
     */
    /*public void tagNote(Note note, String tagName) {
        boolean isTagExist = containsTag(tagName);

        if(!isTagExist) {
            createTag(tagName);
        }

        for(Tag t : tagMap.keySet()) {
            if(t.getTagName().equalsIgnoreCase(tagName)) {
                //note.getTags().add(t);
                tagMap.get(t).add(note);
                break;
            }
        }
    }*/

    /**
     * Tags a Note with the provided name. Creates a new Tag if the Tag does not exist.
     *
     * @param note Note to be tagged.
     * @param tagName name of the Tag.
     * @param tagColor color of the Tag.
     */
    public void tagNote(Note note, String tagName, String tagColor) {
        boolean isTagExist = containsTag(tagName);

        if(!isTagExist) {
            createTag(tagName, tagColor);
        }

        for(Tag t : tagMap.keySet()) {
            if(t.getTagName().equalsIgnoreCase(tagName)) {
                note.getTags().add(t);
                tagMap.get(t).add(note);
                break;
            }
        }
    }

    /**
     * Tags a Note with the provided Tag.
     *
     * @param note Note to be tagged.
     * @param tag Provided Tag.
     */
    public void tagNote(Note note, Tag tag) {
        tagMap.get(tag).add(note);
        note.getTags().add(tag);
    }

    /**
     * Removes a Tag from the Note.
     *
     * @param note Note to be untagged.
     * @param tagName name of the Tag.
     */
    public void removeTag(Note note, String tagName) {
        for (Tag t : tagMap.keySet()) {
            if (t.getTagName().equalsIgnoreCase(tagName)) {
                //note.getTags().remove(t);
                tagMap.remove(t, note);
            }
        }
    }

    /**
     * Removes a Tag from the Note.
     *
     * @param note Note to be untagged.
     * @param tag to be removed.
     */
    public void removeTag(Note note, Tag tag) {
        tagMap.remove(tag, note);
    }

    /**
     * Deletes a Tag from the Map. Notes that have the Tag will be untagged.
     *
     * @param tagName name of the Tag to be deleted.
     */
    public void deleteTag(String tagName) {
        for (Tag t : tagMap.keySet()) {
            if (t.getTagName().equalsIgnoreCase(tagName)) {
                for (Note n : tagMap.get(t)) {
                    //n.getTags().remove(t);
                }
                tagMap.remove(t);
                break;
            }
        }
    }

    /**
     * Lists all the Tags in the map.
     *
     * @return all the Tags.
     */
    public String listTags() {
        String tagList = "";
        for (Tag t : tagMap.keySet()) {
            tagList = tagList.concat(t.toString() + InterfaceManager.LS);
        }
        return tagList;
    }
}