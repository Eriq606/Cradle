package cradle;
import java.io.FileNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class CradleServlet extends HttpServlet{
    private DispatchSwan swan;
    
    @Override
    public void init() throws ServletException {
        try {
            setSwan(DispatchSwan.getSwan(this));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DispatchSwan getSwan() {
        return swan;
    }

    public void setSwan(DispatchSwan swan) {
        this.swan = swan;
    }
}
