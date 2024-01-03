package [appnamemin].controllers;

import cradle.DispatchSwan;
import eriq.flamework.annotations.Controller;
import eriq.flamework.annotations.URLMapping;
import eriq.flamework.model.ModelView;
import eriq.flamework.servlet.ServletEntity;

@Controller
public class HomeController {
    @URLMapping("home.do")
    public ModelView home(ServletEntity entity) throws Exception{
        ModelView model=new ModelView();
        model.addItem("view", "home.jsp");
        DispatchSwan swan=DispatchSwan.getSwan(entity.getContextPath());
        model.addItem("routes", swan.getSidebar_routes());
        model.addItem("tabtitle", "Welcome");
        model.setView(swan.getLayout());
        return model;
    }
}
