package de.wethinkco.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static java.awt.Color.white;

public class Main {
    static ObjectMapper objectMapper = new ObjectMapper();

    static void objectToJson(Car car) throws IOException {
        objectMapper.writeValue(new File("target/car.json"), car);
    }

    static String objectToJsonString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    static Car jsonToObject(String jsonString) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, Car.class);
    }

    static JsonNode jsonToJsonNode(String jsonString)
            throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

    static void jsonNodeIterator(JsonNode jsonNode) {
        for (Iterator<String> it = jsonNode.fieldNames(); it.hasNext(); ) {
            JsonNode jn = jsonNode.get(it.next());
            if (jn.getNodeType() == JsonNodeType.OBJECT) {
                jsonNodeIterator(jn);
                break;
            }
            System.out.println(jn);
        }
    }

    public static void main(String[] args) throws IOException{
        objectToJson(new Car("yellow", "renault"));

        System.out.println(
                objectToJsonString(new Car("red", "ferrari"))
        );

        System.out.println(
                jsonToObject(
                        "{\"colour\":\"pink\",\"type\":\"toyota\"}"
                )
        );

        JsonNode jsonNode =
                jsonToJsonNode(
                        "{\"colour\":\"white\",\"type\":\"nissan\"}"
                );

        for (Iterator<String> it = jsonNode.fieldNames(); it.hasNext(); ) {
            System.out.println(it.next());
        }

        for (JsonNode jn : jsonNode) {
            System.out.println(jn.toString());
        }

        String jsonString = objectToJsonString(
                new Dealership(15000, new Car("blue", "honda"))
        );

        jsonNodeIterator(jsonToJsonNode(jsonString));
    }
}
