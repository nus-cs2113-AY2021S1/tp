package seedu.duke.resources;


/**
 * This class is created to be used in ModuleChecker class only.
 * Here is a sample JSON data from NUSMod API:
 *   {
 *     "moduleCode": "ACC1002",
 *     "title": "Financial Accounting",
 *     "semesters": [
 *       1,
 *       2
 *     ]
 *   }
 * We are only interested in the moduleCode and nothing else.
 * If need to retrieve more information from NUSMod API,
 * Amend the class methods accordingly.
 */
public class NusModule {
    private String moduleCode;

    /**
     * Default constructor is needed for ModuleChecker.
     * Fastjson is used in ModuleChecker, and the parser method in the fastjson library requires this.
     */
    public NusModule() {
    }

    /**
     * Method needed to set the module code as required by the fastjson library.
     *
     * @param moduleCode sets the moduleCode to the intended module code.
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Method getter to return the module code to be used in the subsequent mapping.
     * After parsing the json file from NUSMod API, the module code and the relevant information
     * will be stored in a HashMap.
     *
     * @return moduleCode a string representing the module code.
     */
    public String getModuleCode() {
        return moduleCode;
    }
}

