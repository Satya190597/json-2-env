package com.satya.prakash.nandy.json2env.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgoodies.common.base.Strings;
import com.satya.prakash.nandy.json2env.exception.JsonMissingException;
import com.satya.prakash.nandy.json2env.exception.JsonValidationException;
import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;
import com.satya.prakash.nandy.json2env.ui.JsonInputComponent;

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


    public String generateEnvText() {
        try {
            if(jsonToEnvRepo.getAllComponent().isEmpty())
                return null;
            validateAllTheInputJson();
            List<JsonInputComponent> jsonInputComponentList = jsonToEnvRepo.getAllComponent();
            StringBuilder envString = new StringBuilder();
            for(JsonInputComponent component : jsonInputComponentList) {
                Map<String,Object> result = objectMapper.readValue(component.getJsonTextField().getText(),Map.class);
                buildEnvStringFromMap(component.getPrefixTextField().getText(),result,envString);
            }
            return envString.toString();
        }
        catch (JsonMissingException | JsonValidationException exception) {
            throw exception;
        }
        catch (Exception exception) {
            throw new RuntimeException("Something went wrong while processing data.");
        }
    }
    private void buildEnvStringFromMap(String prefix,Map<String,Object> map,StringBuilder stringBuilder) throws JsonProcessingException {
        for(Map.Entry<String,Object> entry : map.entrySet()) {
            if (Strings.isBlank(prefix))
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
            else
                stringBuilder.append(prefix.length()>200 ? prefix.substring(0,200) : prefix).append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
    }
    private void validateAllTheInputJson()  {
        try {
            List<JsonInputComponent> jsonInputComponentList = jsonToEnvRepo.getAllComponent();
            for (JsonInputComponent component : jsonInputComponentList) {
                if(Strings.isBlank(component.getJsonTextField().getText()))
                   throw new JsonMissingException("JSON input is missing for few fields");
                objectMapper.readTree(component.getJsonTextField().getText());
            }
        }
        catch (JsonMissingException jsonMissingException) {
            throw jsonMissingException;
        }
        catch (Exception exception) {
            throw new JsonValidationException("Invalid Json Provided.");
        }
    }
}
