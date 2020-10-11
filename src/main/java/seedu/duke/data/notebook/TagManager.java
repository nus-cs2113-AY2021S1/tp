package seedu.duke.data.notebook;

import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a TagManager. Manages the tags for the notes.
 */
public class TagManager {

    private static final String STRING_TAG_EMPTY = "There are no tags!";
    private static final String STRING_TAG_LIST = "Here are the available tags:" + InterfaceManager.LS;

    private Map<Tag, ArrayList<Note>> tagMap;

    public TagManager() {
        tagMap = new HashMap<>();
    }

    public Map<Tag, ArrayList<Note>> getTagMap() {
        return tagMap;
    }

    /**
     * Returns if a tag with the tag name exists.
     *
     * @param tagName name of the Tag to check.
     * @return true if Tag exists, false otherwise.
     */
    private boolean containsTag(String tagName) {
        for (Tag t : tagMap.keySet()) {
            if (t.getTagName().equalsIgnoreCase(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Tag that matches the tag name.
     *
     * @param tagName name of the Tag to check.
     * @return the Tag if it exists, null otherwise.
     */
    public Tag getTag(String tagName) {
        for (Tag t : tagMap.keySet()) {
            if (t.getTagName().equalsIgnoreCase(tagName)) {
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

        if (!isTagExist) {
            tagMap.put(new Tag(tagName), new ArrayList<>());
        }
    }

    /**
     * Creates a Tag with the provided name and color.
     *
     * @param tagName name of the Tag.
     * @param tagColor color of the Tag.
     * @return true if new Tag is created, false otherwise.
     */
    public boolean createTag(String tagName, String tagColor) {
        if (!containsTag(tagName)) {
            tagMap.put(new Tag(tagName, tagColor), new ArrayList<>());
            return true;
        } else {
            getTag(tagName).setTagColor(tagColor);
            return false;
        }
    }

    /**
     * Creates a Tag with the provided Tag.
     *
     * @param tag provided Tag.
     * @param overridesColor determine if the tag color needs to be override.
     * @return true if new Tag is created, false otherwise.
     */
    public boolean createTag(Tag tag, boolean overridesColor) {
        // Check if there exist a tag with the same tag name.
        Tag existingTag = getTag(tag.getTagName());

        // If the tag does not exist, creates it.
        if (existingTag == null) {
            tagMap.put(tag, new ArrayList<>());
            return true;
        } else {
            if (overridesColor) {
                existingTag.setTagColor(tag.getTagColor());
            }
            return false;
        }
    }

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

        if (!isTagExist) {
            createTag(tagName, tagColor);
        }

        Tag tag = getTag(tagName);
        tagNote(note, tag);
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
        Tag tag = getTag(tagName);

        if (tag != null) {
            removeTag(note, tag);
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
        note.getTags().remove(tag);
    }

    /**
     * Deletes a Tag from the Map. Notes that have the Tag will be untagged.
     *
     * @param tagName name of the Tag to be deleted.
     * @return true if there exist the tag and is deleted, false otherwise.
     */
    public boolean deleteTag(String tagName) {
        Tag tag = getTag(tagName);

        if (tag == null) {
            return false;
        }

        for (Note n : tagMap.get(tag)) {
            n.getTags().remove(tag);
        }
        tagMap.remove(tag);
        return true;
    }

    /**
     * Deletes a Tag from the Map. Notes that have the Tag will be untagged.
     *
     * @param tag to be deleted.
     * @return true if there exist the tag and is deleted, false otherwise.
     */
    public boolean deleteTag(Tag tag) {
        Tag existingTag = getTag(tag.getTagName());

        if (existingTag == null) {
            return false;
        }

        for (Note n : tagMap.get(existingTag)) {
            n.getTags().remove(existingTag);
        }
        tagMap.remove(existingTag);
        return true;
    }

    /**
     * Lists all the Tags in the map.
     *
     * @return all the Tags as string.
     */
    public String listTags() {
        String tagList;

        if (tagMap.isEmpty()) {
            return STRING_TAG_EMPTY;
        } else  {
            tagList = STRING_TAG_LIST;
        }

        for (Tag t : tagMap.keySet()) {
            tagList = tagList.concat(t.toString() + InterfaceManager.LS);
        }
        return tagList.trim();
    }

    /**
     * Rebinds all the tags in the note to the existing tags in the database.
     *
     * @param note the note to have the tags rebind.
     */
    public void rebindTags(Note note) {
        int numTagsToCheck = note.getTags().size();

        // loop through all the tags in notes
        for (int i = 0; i < numTagsToCheck; ++i) {

            // always check against the tag of the first note
            Tag tag = note.getTags().get(0);
            // check if the tag exists in the database
            Tag existingTag = getTag(tag.getTagName());
            note.getTags().remove(tag);

            if (existingTag == null) {
                // if the tag does not exist in the database, create the tag and tag to note
                createTag(tag, false);
                tagNote(note, tag);
            } else if (!note.getTags().contains(existingTag)) {
                tagNote(note, existingTag);
            }
        }
    }
}