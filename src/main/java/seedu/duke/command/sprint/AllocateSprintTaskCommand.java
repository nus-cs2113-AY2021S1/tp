package seedu.duke.command.sprint;

import seedu.duke.model.member.Member;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Arrays;
import java.util.Hashtable;

public class AllocateSprintTaskCommand extends SprintCommand {

    public AllocateSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
    }

    public void execute() {
        chooseProject();
        Ui.showToUserLn(this.projOwner.toIdString());
        chooseSprint();
        Ui.showToUserLn(this.sprintOwner.toIdString());

        String[] taskIds = this.parameters.get("task").split(" ");
        String[] userIds = this.parameters.get("user").split(" ");
        for (String taskIdInString : taskIds) {
            int taskId = Integer.parseInt(taskIdInString);
            for (String userId : userIds) {
                Member mem = this.projOwner.getProjectMember().getMember(userId.trim());
                mem.allocateTask(taskId);
                this.projOwner.getProjectBacklog().getTask(taskId).allocateToMember(mem.getUserId());
            }
            Ui.showToUserLn(this.projOwner.getProjectBacklog().getTask(taskId).getTitle()
                    + " is assigned to "
                    + Arrays.toString(userIds));
        }
    }
}
