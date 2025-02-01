package com.satya.prakash.nandy.json2env.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgoodies.common.base.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class JsonToEnvProcessorTest {
    @Test
    void algorithmTest() throws Exception {
        Map<String,Object> testData = new ObjectMapper().readValue(getClass().getResource("/test-data.json"), Map.class);
        StringBuilder result = new StringBuilder();
        myAlgorithm(testData,result,"");
        System.out.println(result.toString());
        Assertions.assertTrue(true);
    }
    void myAlgorithm(Map<String,Object> data,StringBuilder br,String prefix) {
        for(Map.Entry entry : data.entrySet()) {
            if(entry.getValue() instanceof Map)
                myAlgorithm((Map<String,Object>)entry.getValue(),br,prefix.concat(entry.getKey().toString()).concat("."));
            if(Strings.isBlank(prefix))
                br.append(String.format("%s=%s | ",entry.getKey(),entry.getValue()));
            else
                br.append(String.format("%s=%s | ",prefix.concat(entry.getKey().toString()),entry.getValue()));
        }
    }
}