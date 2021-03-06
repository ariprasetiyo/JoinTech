package arprast.qiyosq.controller.publicv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ari-prasetiyo
 */
@Controller
public class HomeController {
    
    @RequestMapping(value = {"/", "/index", "/index.html"} , method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }
        if (logout != null) {
            model.addObject("message", "Logged out from AG successfully.");
        }
        model.setViewName("login/login");
        return model;
    }
}
