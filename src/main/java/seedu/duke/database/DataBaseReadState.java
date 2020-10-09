package seedu.duke.database;


/***
 * This class is used when data base is reading the data from the file
 */
public enum DataBaseReadState {
    CANTEEN,
    STORE,
    FOOD;

    /***
     * lowers the initial state from e.g. CANTEEN ----> STORE
     * There will be at most 4 states so this can just be hard coded
     * @param initialState the initial state
     * @return the state lowered by 1 level, or the same state if there is no lower state
     */
    public static DataBaseReadState lowerState(DataBaseReadState initialState){
        DataBaseReadState loweredState;
        switch (initialState){
        case CANTEEN:
            loweredState = STORE;
            break;
        case STORE:
            loweredState = FOOD;
            break;
        default:
            loweredState = initialState;
            break;
        }
        return loweredState;
    }

    public static DataBaseReadState raiseState(DataBaseReadState initialState){
        DataBaseReadState raisedState;
        switch (initialState){
        case STORE:
            raisedState = CANTEEN;
            break;
        case FOOD:
            raisedState = STORE;
            break;
        default:
            raisedState = initialState;
            break;
        }
        return raisedState;
    }
}
