package gwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * �û���¼
 * @author Lbl
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	@RequestMapping("login") // @RequestMapping ע�������ָ����URL·�����ʱ����Ʋ�
    public String login(@RequestParam("Username") String Username, @RequestParam("Password") String Password,Model model) {

        if (Username.equals("admin") && Password.equals("admin")) {
            model.addAttribute("username", Username);
            return "/main";
        } else {
            model.addAttribute("username", Username);
            return "no.jsp";
        }
    }
}
