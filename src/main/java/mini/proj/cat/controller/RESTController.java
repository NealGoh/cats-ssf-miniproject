package mini.proj.cat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini.proj.cat.service.RepoService;

@RestController
@RequestMapping(path = "/api")
public class RESTController {
    @Autowired
    private RepoService repoSvc;
    
    @PostMapping(path = "/addcat")
    public void addCat(@RequestBody MultiValueMap<String, String> form, HttpServletResponse response) throws IOException {
        String catId = form.getFirst("catid");
        String user = form.getFirst("user");
        repoSvc.addCat(catId, user);
        response.sendRedirect("/successful");
    }

    @PostMapping(path = "/profile")
    public void retrieveProfile(@RequestBody MultiValueMap<String, String> form, HttpServletResponse response) throws IOException {
        String user = form.getFirst("user");
        String catId = repoSvc.retrieveProfile(user);
        response.sendRedirect("/%s/%s".formatted(user, catId));
    }
}
