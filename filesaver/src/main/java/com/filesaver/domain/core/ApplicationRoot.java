package com.filesaver.domain.core;

import com.filesaver.domain.core.DTO.Name;
import com.filesaver.sevrvices.JacksonAdapter;
import com.filesaver.utils.ConfigUtil;

public class ApplicationRoot extends Directory {
  private static ApplicationRoot instance;

  private ApplicationRoot(Name name) throws Exception {
    super(name);
  }


  public static void createInstance (Name name, JacksonAdapter configFile) throws Exception{
    if(instance == null){
      instance = new ApplicationRoot(name);

      ConfigUtil config = configFile.getConfigs();
      instance.setPath(config.getApplication_root());

    } else {
        throw new IllegalStateException("AppicationRoot já foi inicializado e não pode ser reinicializado.");
    }
  }
  
  public static ApplicationRoot getInstance() throws Exception{
    if(instance == null){
      throw new IllegalStateException("AppicationRoot ainda não foi inicializado. Use o inicializador.");
    }
    return instance;
  }
}
