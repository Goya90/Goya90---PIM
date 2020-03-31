package presentation.gotoCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class GoToDownloadFileCommand extends Command {
    
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
               
        return "DownloadFile";
    }
    
}
