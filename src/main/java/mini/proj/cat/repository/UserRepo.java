package mini.proj.cat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    @Qualifier("repository")
    private RedisTemplate<String, String> redisTemplate;

    public void addCat(String catId, String user) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(user, catId);
    }

    public String retrieveProfile(String user) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(user);
    }
}
