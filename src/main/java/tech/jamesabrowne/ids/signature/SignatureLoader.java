package tech.jamesabrowne.ids.signature;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class SignatureLoader {
    public List<Signature> loadSignatures() throws Exception {
        Gson gson = new Gson();
        ClassLoader classLoader = getClass().getClassLoader();
        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("signatures.json"))) {
            return gson.fromJson(reader, new TypeToken<List<Signature>>() {}.getType());
        }
    }
}
