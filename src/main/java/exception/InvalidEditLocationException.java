package exception;

public class InvalidEditLocationException extends InvalidCommandException {
    @Override
    public String getMessage() {
        return "Enter the location in the format /l LOCATION for offline location and /o LINK /p PASSWORD for "
                + "online location.";
    }
}
