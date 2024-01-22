package config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import house.House;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HouseConfig {
    private House house;

    public HouseConfig(House house) {
        this.house = house;
    }

    private String createJsonString(House house) {
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("{\n");
        jsonBuilder.append("  \"address\": ").append(jsonValue(house.getAddress().toString())).append(",\n");
        jsonBuilder.append("  \"area\": ").append(house.getArea()).append(",\n");
        jsonBuilder.append("  \"numberOfRooms\": ").append(house.getNumberOfRooms()).append(",\n");

        long personCount = house.getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getPeople().stream())
                .count();


        String floorsJson = house.getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getPeople().stream())
                .map(person -> jsonValue(person.getName()))
                .collect(Collectors.joining(", "));
        jsonBuilder.append("  \"people\": [").append(floorsJson).append("],\n");


        String petsJson = house.getPets().stream()
                .map(pet -> jsonValue(pet.getNickname()))
                .collect(Collectors.joining(", "));
        jsonBuilder.append("  \"pets\": [").append(petsJson).append("]\n");

        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }

    private String jsonValue(String value) {
        return "\"" + value + "\"";
    }


    public void generateConfig() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = "src/main/java/configs/" + timestamp + ".json";

        Gson gson = new Gson();
        String json = jsonValue(createJsonString(house));
        String result = json.substring(1, json.length() - 1);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JsonObject loadConfig(String filePath){
        JsonObject jsonObject = null;
        try {
            jsonObject = readJsonFromFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    private static JsonObject readJsonFromFile(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        return JsonParser.parseReader(reader).getAsJsonObject();
    }

    public static List<String> convertJsonArrayToList(com.google.gson.JsonArray jsonArray) {
        List<String> list = new ArrayList<>();
        jsonArray.forEach(element -> list.add(element.getAsString()));
        return list;
    }
}
