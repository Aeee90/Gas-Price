package aeee.api.gasprice.web.api;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.*;

import javax.annotation.PostConstruct;

@Slf4j
abstract class HttpSender implements ResponseErrorHandler {

    @Autowired
    private Environment environment;

    protected String URL;
    private final String _urlKey;
    private HttpHeaders headers = new HttpHeaders();


    public HttpSender(String urlKey){
        _urlKey = urlKey;
    }

    @PostConstruct
    public void init() {
        URL = environment.getProperty(_urlKey);
        log.info("Set {}", URL);
        if(URL == null) throw new IllegalArgumentException("URL Is Null.");

        _setHeader();
        if(headers == null) throw new IllegalArgumentException(" HttpHeaders Must Not be Null.");
    }

    private void _setHeader(){
        headers = setHeader(headers);
    }
    abstract protected HttpHeaders setHeader(HttpHeaders headers);


    protected <T> HttpEntity<T> getHttpEntity(T data){
        return new HttpEntity<>(data, headers);
    }

    protected <T> HttpEntity<T> getHttpEntity(T data, CustomizHttpHeader customizHttpHeader){
        HttpHeaders newHeaders = setHeader(new HttpHeaders());
        return new HttpEntity<>(data, customizHttpHeader.customizeHttpHeaders(newHeaders));
    }

    protected  <T> T post(@Nullable Object request, Class<T> clazz) {
        return new RestTemplate().postForEntity(URL, request, clazz).getBody();
    }

    protected interface CustomizHttpHeader {
        HttpHeaders customizeHttpHeaders(HttpHeaders headers);
    }

}
