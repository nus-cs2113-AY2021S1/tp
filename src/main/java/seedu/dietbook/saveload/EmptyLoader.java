package seedu.dietbook.saveload;

import java.util.Optional;

/**
 * Place holder class for Loader, does not do anything
 * Throws IllegalAccessException if there is any attempt to access this class.
 */
public class EmptyLoader extends Loader {
    protected EmptyLoader(){
    }

    @Override
    public Optional<String> get(int xposition, int yposition) throws IllegalAccessException {
        throw new IllegalAccessException("Do not attempt to get from an empty loader!");
    }

    @Override
    int getHeight() throws IllegalAccessException {
        throw new IllegalAccessException("Do not attempt to get from an empty loader!");
    }

    @Override
    int getWidth() throws IllegalAccessException {
        throw new IllegalAccessException("Do not attempt to get from an empty loader!");
    }
}
