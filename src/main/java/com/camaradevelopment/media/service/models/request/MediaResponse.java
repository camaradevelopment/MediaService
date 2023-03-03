package com.camaradevelopment.media.service.models.request;

import com.camaradevelopment.media.service.models.media.Media;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class MediaResponse implements HttpResponse<Object> {

    private int statusCode;

    private HttpRequest request;

    private HttpHeaders httpHeaders;

    private Object body;


    public MediaResponse(int statusCode, HttpRequest request, HttpHeaders httpHeaders, Object body) {
        this.statusCode = statusCode;
        this.request = request;
        this.httpHeaders = httpHeaders;
        this.body = body;
    }

    public MediaResponse(int statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public MediaResponse() {
    }

    @Override
    public int statusCode() {
        return this.statusCode;
    }

    @Override
    public HttpRequest request() {
        return this.request;
    }



    @Override
    public Optional<HttpResponse<Object>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return this.httpHeaders;
    }

    @Override
    public Object body() {
        return this.body;
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public HttpClient.Version version() {
        return null;
    }
}
