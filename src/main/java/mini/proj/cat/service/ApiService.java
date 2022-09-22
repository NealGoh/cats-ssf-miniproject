package mini.proj.cat.service;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import mini.proj.cat.model.Cat;

@Service
public class ApiService {
    
    private ResponseEntity<String> fetch(String url) {
        RestTemplate template = new RestTemplate();
        RequestEntity<Void> req = RequestEntity.get(url).build();
        try {
            ResponseEntity<String> res = template.exchange(req, String.class);
            return res;
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }

    private JsonObject readApiResponse(ResponseEntity<String> apiResponse) {
        String s = apiResponse.getBody();
        Reader reader = new StringReader(s);
        JsonReader jr = Json.createReader(reader);
        return jr.readObject();
    }

    private JsonArray readArrApiResponse(ResponseEntity<String> apiResponse) {
        String s = apiResponse.getBody();
        Reader reader = new StringReader(s);
        JsonReader jr = Json.createReader(reader);
        JsonArray array = jr.readArray();
        jr.close();
        return array;
    }

    public Cat randomCat() {
        String url = "https://api.thecatapi.com/v1/images/search";
        JsonArray response = readArrApiResponse(fetch(url));
        JsonObject jo = response.getJsonObject(0);
        JsonValue jvCatUrl = jo.get("url");
        JsonValue jvCatId = jo.get("id");
        String catImgUrl = jvCatUrl.toString().replaceAll("\"", "");
        String catId = jvCatId.toString().replaceAll("\"", "");
        Cat cat = new Cat();
        cat.setCatPicUrl(catImgUrl);
        cat.setId(catId);
        return cat;
    }
    
    public String getCatImg(String catId) {
        String url = "https://api.thecatapi.com/v1/images/%s".formatted(catId);
        JsonObject jo = readApiResponse(fetch(url));
        return jo.get("url").toString();
    }
}
