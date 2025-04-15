package com.filesaver.sevrvices;

import com.filesaver.domain.errors.EntityNotFoundError;
import com.filesaver.utils.ConfigUtil;

import java.io.File;
import java.io.FileWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonAdapter {
  private ConfigUtil configs;
  private static JacksonAdapter instance;


  private JacksonAdapter(){
  }

  public static void createInstance () throws IllegalStateException{
     if(instance == null){
      instance = new JacksonAdapter();
    } else {
        throw new IllegalStateException("JacksonAdapter já foi inicializado e não pode ser reinicializado.");
    }
  }

  public static JacksonAdapter getInstance() throws IllegalStateException{
    if(instance == null){
      throw new IllegalStateException("JacksonAdapter ainda não foi inicializado. Use o inicializador.");
    }
    return instance;
  }

  public ConfigUtil getDataFromConfigs(File config) throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    if(config.exists()){
      configs = mapper.readValue(config, ConfigUtil.class);
    } else {
      throw new EntityNotFoundError("File");
    }

    return configs;
  }

  public void setDataToConfigs(ConfigUtil configData, File config) throws Exception {
    if(!config.exists()){
      config = new File("config\\config.json");
      boolean isCreatedJson = config.createNewFile();
      if(!isCreatedJson){
        throw new EntityNotFoundError("File");
      }
    }

    final ObjectMapper mapper = new ObjectMapper();
    final String jsonString = mapper.writeValueAsString(configData);
    
    FileWriter jsonFile = new FileWriter(config);
    jsonFile.write(jsonString);
    jsonFile.close();
    

    this.configs = configData;
  }

  public ConfigUtil getConfigs() throws IllegalStateException{
    if(configs == null){
      throw new IllegalStateException("configs ainda não foi inicializado. Use o getDataFromConfigs passando um File relacionado ao arquivo de configuração.");
    }
    return configs;
  }

  
  
}
