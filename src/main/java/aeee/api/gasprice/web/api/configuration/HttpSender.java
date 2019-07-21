package aeee.api.gasprice.web.api.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.*;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class HttpSender implements ResponseErrorHandler {

    @Autowired
    private Environment environment;

    protected String url;
    private final String urlKey;
    private HttpHeaders headers = new HttpHeaders();


    public HttpSender(String urlKey){
        this.urlKey = urlKey;
    }

    @PostConstruct
    public void init() {
        url = environment.getProperty(urlKey);
        log.info("Set {}", url);
        if(url == null) throw new IllegalArgumentException("URL Is Null.");

        _setHeader();
        if(headers == null) throw new IllegalArgumentException(" HttpHeaders Must Not be Null.");
    }

    private void _setHeader(){
        headers = setHeader(headers);
    }
    abstract protected HttpHeaders setHeader(HttpHeaders headers);


    public <T> HttpEntity<T> getHttpEntity(T data){
        return new HttpEntity<>(data, headers);
    }

    protected <T> HttpEntity<T> getHttpEntity(T data, CustomizeHttpHeader customizeHttpHeader){
        HttpHeaders newHeaders = setHeader(new HttpHeaders());
        return new HttpEntity<>(data, customizeHttpHeader.customizeHttpHeaders(newHeaders));
    }

    public  <T> T post(@Nullable Object request, Class<T> clazz) {
        return new RestTemplate().postForEntity(url, request, clazz).getBody();
    }

    protected interface CustomizeHttpHeader {
        HttpHeaders customizeHttpHeaders(HttpHeaders headers);
    }

}
