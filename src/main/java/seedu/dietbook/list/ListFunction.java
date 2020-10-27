package seedu.dietbook.list;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Functional programming support methods for Lists.
 */
public class ListFunction {
    /**
     * Maps a function across a list.
     * @param list list to operate on
     * @param function function to be mapped across list
     * @return list of mapped items under provided function
     */
    protected static <T, E> ArrayList<E> applyFunctionToList(List<T> list, Function<T, E> function) {
        ArrayList<E> appliedList = new ArrayList<>();
        Consumer<T> addResultToAppliedList = x -> appliedList.add(function.apply(x));
        list.forEach(addResultToAppliedList);
        return appliedList;
    }

    /**
     * Filters the list by the given predicate.
     */
    protected static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
}
