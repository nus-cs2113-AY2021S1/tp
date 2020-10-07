package seedu.duke.data.notebook;

import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a TagManager. Manages the tags for the notes.
 */
public class TagManager {

    private Map<Tag, ArrayList<Note>> tagMap;

    public TagManager() {
        tagMap = new HashMap<>();
    }

    public Map<Tag, ArrayList<Note>> getTagMap() {
        return tagMap;
    }

    /**
     * Checks if the Tag exists in the Map.
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
     */
    public boolean createTag(String tagName, String tagColor) {
        boolean isTagExist = containsTag(tagName);

        if (!isTagExist) {
            tagMap.put(new Tag(tagName, tagColor), new ArrayList<>());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates a Tag with the provided Tag.
     *
     * @param tag provided Tag.
     */
    public void createTag(Tag tag) {
        tagMap.put(tag, new ArrayList<>());

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

    public void rebindTags(Note note) {
        int numTagsToCheck = note.getTags().size();
        for (int i = 0; i < numTagsToCheck; ++i) {
            Tag tag = note.getTags().get(0);
            Tag existingTag = getTag(tag.getTagName());

            note.getTags().remove(tag);

            if (existingTag == null) {
                createTag(tag);
                tagNote(note, tag);
            } else if (!note.getTags().contains(existingTag)) {
                tagNote(note, existingTag);
            }
        }
    }
}