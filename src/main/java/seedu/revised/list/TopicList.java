package seedu.revised.list;

import seedu.revised.card.Topic;

import java.util.List;

public class TopicList extends BaseList<Topic> {
    public TopicList(List<Topic> topics) {
        super(topics);
    }

    /**
     * Gets the <code>Topic</code> at the index.
     *
     * @param index index number of the topic
     * @return <code>Topic</code> with the index number
     */
    public Topic get(int index) {
        return this.list.get(index);
    }
}
