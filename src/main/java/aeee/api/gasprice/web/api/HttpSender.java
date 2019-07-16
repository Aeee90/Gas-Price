package aeee.api.gasprice.web.api;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
abstract class HttpSender {

    @Autowired
    private Environment environment;

    private String URL;
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

    protected <T> HttpEntity<T> getHttpEntity(T data, GenerateHttpHeader generateHttpHeader){
        HttpHeaders newHeaders = setHeader(headers);
        return new HttpEntity<>(data, generateHttpHeader.generateHttpHeaders(newHeaders));
    }

    protected  <T> T postForObject(@Nullable Object request, Class<T> clazz) throws RestClientException {
        return new RestTemplate().postForObject(URL, request, clazz);
    }

    protected interface GenerateHttpHeader {
        HttpHeaders generateHttpHeaders(HttpHeaders headers);
    }


}
