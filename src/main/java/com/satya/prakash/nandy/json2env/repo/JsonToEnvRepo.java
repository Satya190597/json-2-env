package com.satya.prakash.nandy.json2env.repo;

import com.satya.prakash.nandy.json2env.ui.JsonInputComponent;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JsonToEnvRepo {
    final Set<JsonInputComponent> listOfJsonInputComponent =  ConcurrentHashMap.newKeySet();
    private boolean readNestedJson = false;
    private boolean readArrayAsMultiValue = false;
    private static volatile JsonToEnvRepo jsonToEnvRepo;
    private JsonToEnvRepo() {
    };
    public static JsonToEnvRepo getRepo() {
        if(Objects.isNull(jsonToEnvRepo)) {
            synchronized (JsonToEnvRepo.class) {
                if (Objects.isNull(jsonToEnvRepo)) {
                    jsonToEnvRepo = new JsonToEnvRepo();
                }
            }
        }
        return jsonToEnvRepo;
    }
    public void addComponent(JsonInputComponent jsonInputComponent) {
        synchronized (listOfJsonInputComponent) {
            if (listOfJsonInputComponent.size() < 12)
                listOfJsonInputComponent.add(jsonInputComponent);
        }
    }
    public void removeComponent(JsonInputComponent jsonInputComponent) {
        synchronized (listOfJsonInputComponent) {
            if (listOfJsonInputComponent.isEmpty())
                return;
            listOfJsonInputComponent.remove(jsonInputComponent);
        }
    }
    public List<JsonInputComponent> getAllComponent() {
        synchronized (listOfJsonInputComponent) {
            return listOfJsonInputComponent.stream().toList();
        }
    }
    public boolean isLimitExceed() {
        synchronized (listOfJsonInputComponent) {
            return listOfJsonInputComponent.size()>=12;
        }
    }
    public void reset() {
        synchronized (JsonToEnvRepo.class) {
            jsonToEnvRepo = null;
        }
    }
    public boolean isReadNestedJson() {
        synchronized (JsonToEnvRepo.class) {
            return readNestedJson;
        }
    }
    public void setReadNestedJson(boolean state) {
        synchronized (JsonToEnvRepo.class) {
            readNestedJson = state;
        }
    }
    public void setReadArrayAsMultiValue(boolean state) {
        synchronized (JsonToEnvRepo.class) {
            readArrayAsMultiValue = state;
        }
    }
    public boolean isReadArrayAsMultiValue() {
        synchronized (JsonToEnvRepo.class) {
            return readArrayAsMultiValue;
        }
    }

}
