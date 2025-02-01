package com.satya.prakash.nandy.json2env.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgoodies.common.base.Strings;
import com.satya.prakash.nandy.json2env.exception.JsonMissingException;
import com.satya.prakash.nandy.json2env.exception.JsonValidationException;
import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;
import com.satya.prakash.nandy.json2env.ui.JsonInputComponent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonToEnvProcessor {
    private final JsonToEnvRepo jsonToEnvRepo;
    private final ObjectMapper objectMapper;

    public JsonToEnvProcessor() {
        this.jsonToEnvRepo = JsonToEnvRepo.getRepo();
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    /**
     * Main function responsible for generating environment text.
     * First, it validates all the input controls.
     * Once all inputs are validated, it processes the JSON inputs, converts them,
     * and combines them to create a single environment text.
     */
    public String generateEnvText() {
        try {
            if (jsonToEnvRepo.getAllComponent().isEmpty())
                return null;
            validateAllTheInputJson();
            List<JsonInputComponent> jsonInputComponentList = jsonToEnvRepo.getAllComponent();
            StringBuilder envString = new StringBuilder();
            for (JsonInputComponent component : jsonInputComponentList) {
                if (jsonToEnvRepo.isReadNestedJson()) {
                    JsonNode rootNode = objectMapper.readTree(component.getJsonTextField().getText());
                    Map<String, Object> result = new HashMap<>();
                    flattenJson(rootNode, "", result);
                    buildEnvStringFromMap(component.getPrefixTextField().getText(), result, envString);
                } else {
                    Map<String, Object> result = objectMapper.readValue(component.getJsonTextField().getText(), Map.class);
                    buildEnvStringFromMap(component.getPrefixTextField().getText(), result, envString);
                }
            }
            return envString.toString();
        } catch (JsonMissingException | JsonValidationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new RuntimeException("Something went wrong while processing data.");
        }
    }

    private void buildEnvStringFromMap(String prefix, Map<String, Object> map, StringBuilder stringBuilder) throws JsonProcessingException {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (Strings.isBlank(prefix))
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
            else
                stringBuilder.append(prefix.length() > 200 ? prefix.substring(0, 200) : prefix).append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
    }

    /**
     * Retrieve all the text field components from the UI and check whether JSON input is provided.
     * Also, verify if the JSON format is correct.
     * If any of the above validations fail, show an error.
     */
    private void validateAllTheInputJson() {
        try {
            List<JsonInputComponent> jsonInputComponentList = jsonToEnvRepo.getAllComponent();
            for (JsonInputComponent component : jsonInputComponentList) {
                if (Strings.isBlank(component.getJsonTextField().getText()))
                    throw new JsonMissingException("JSON input is missing for few fields");
                objectMapper.readTree(component.getJsonTextField().getText());
            }
        } catch (JsonMissingException jsonMissingException) {
            throw jsonMissingException;
        } catch (Exception exception) {
            throw new JsonValidationException("Invalid Json Provided.");
        }
    }

    /**
     * Recursively flattens a JSON object into a map with dot-delimited keys.
     *
     * @param node      The current JSON node to process.
     * @param parentKey The prefix key representing the current nesting level.
     *                  This is used to generate dot-delimited keys for nested fields.
     * @param map       The map where the flattened key-value pairs are stored.
     *                  Logic:
     *                  1. Iterate through all fields of the current JSON node.
     *                  2. For each field:
     *                      a. Construct a dot-delimited key using the parent key and the current field name.
     *                      b. If the field is an object, recursively call the method to process its fields.
     *                      c. If the field is an array:
     *                          - Check the `isReadArrayAsMultiValue` flag:
     *                              i. If true, process each array element and append the index to the key.
     *                              ii. If false, map the entire array to the key.
     *                      d. If the field is a value node (e.g., string, number), add the key-value pair to the map.
     */
    private void flattenJson(JsonNode node, String parentKey, Map<String, Object> map) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            // 2.a Construct a dot-delimited key using the parent key and the current field name.
            String key = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
            // 2.b If the field is an object, recursively call the method to process its fields.
            if (entry.getValue().isObject()) {
                flattenJson(entry.getValue(), key, map);
            }
            // 2.c If the field is an array:
            else if (entry.getValue().isArray()) {
                // 2.c.i If true, process each array element and append the index to the key.
                if (jsonToEnvRepo.isReadArrayAsMultiValue()) {
                    int index = 0;
                    for (JsonNode element : entry.getValue()) {
                        if(element.isValueNode())
                            map.put(key.concat(".").concat(String.valueOf(index+1)), element);
                        else
                            flattenJson(element, key.concat(".").concat(String.valueOf(index+1)), map);
                        index++;
                    }
                }
                // 2.c.ii If false true, map the entire array to the key.
                else {
                    map.put(key, entry.getValue());
                }
            }
            // 2.d If the field is a value node (e.g., string, number), add the key-value pair to the map.
            else if (entry.getValue().isValueNode()) {
                map.put(key, entry.getValue().asText());
            }
        }
    }
}
