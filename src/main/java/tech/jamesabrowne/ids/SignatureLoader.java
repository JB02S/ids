package tech.jamesabrowne.ids;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SignatureLoader {
    public static List<Signature> loadSignatures() throws Exception{

        List<Signature> signatures = null;

        try {
            Gson gson = new Gson();
            String signaturesDir = System.getProperty("user.dir") + "\\src\\resources\\signatures.json";
            String jsonContent = new String(Files.readAllBytes(Paths.get(signaturesDir)));
            Type signaturesListType = new TypeToken<List<Signature>>() {}.getType();
            signatures = gson.fromJson(jsonContent, signaturesListType);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return signatures;
    }
}
