package cn.test.rest;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class RestApplication extends ResourceConfig {  
    public RestApplication() {  
   
     //服务类所在的包路径  
     packages("cn.test.rest");  
     //注册JSON转换器  
     register(JacksonJsonProvider.class);  
   
    }  
}  
