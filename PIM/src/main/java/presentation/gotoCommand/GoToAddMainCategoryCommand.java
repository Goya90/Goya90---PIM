package presentation.gotoCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Malthe
 */
public class GoToAddMainCategoryCommand extends Command {
    
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        
        return "AddMainCategory";
    }
    
}
