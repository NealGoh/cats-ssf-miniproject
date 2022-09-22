package mini.proj.cat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mini.proj.cat.model.Cat;
import mini.proj.cat.service.ApiService;

@Controller
@RequestMapping
public class CatController {

    @Autowired
    private ApiService apiSvc;
    
   @GetMapping(path = "/randomcat")
   public String searchRandomCat(Model model) {

    Cat cat = apiSvc.randomCat();

    model.addAttribute("catimg", cat.getCatPicUrl());
    model.addAttribute("catid", cat.getId());
    return "randomcat";
   }

   @GetMapping(path = "/login")
   public String login() {
       return "login";
   }

   @GetMapping(path = "/{user}/{catid}")
   public String profilePage(
       @PathVariable(value = "user") String user, 
       @PathVariable(value = "catid") String catId ,
       Model model) {

        String catImgUrl = apiSvc.getCatImg(catId).replaceAll("\"", "");

        model.addAttribute("catimg", catImgUrl);
        model.addAttribute("user", user);
       return "profile";
   }

   @GetMapping(path = "/successful")
   public String successful() {
       return "success";
   }
}
