package com.example.demo.customEndpoints;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Endpoint(id = "customInfo")
public class customInfo {
Map<String, List<String>> info=new HashMap<>();

@PostConstruct
void initialiseInfo(){
    info.put("Info1", new ArrayList<>(Arrays.asList("Service Version is 1.2.3","Service Health is Good")));
    info.put("Info2", new ArrayList<>(Arrays.asList("Service Version is 1.2.4","Service Health is Good")));

}
    @ReadOperation
    Map<String, List<String>> getInfo(){
        return info;
    }

    @ReadOperation
    //@selector acts as pathvariable
    List<String> getSpecificInfo(@Selector String key){
        return info.get(key);
    }

    @WriteOperation
    List<String> addInfo(@Selector String key,String infoDetails){
        System.out.println("infoDetails "+infoDetails);
    List<String>l=info.getOrDefault(key,new ArrayList<>());
    l.add(infoDetails);
    info.put(key,l);
    return l;
    }
}
