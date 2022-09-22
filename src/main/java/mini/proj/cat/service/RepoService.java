package mini.proj.cat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mini.proj.cat.repository.UserRepo;

@Service
public class RepoService {
    @Autowired
    private UserRepo repo;
    
    public void addCat(String catId, String user) {
        repo.addCat(catId, user);
    }

    public String retrieveProfile(String user) {
        return repo.retrieveProfile(user);
    }
}
