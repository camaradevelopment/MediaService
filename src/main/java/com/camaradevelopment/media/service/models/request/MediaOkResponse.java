package com.camaradevelopment.media.service.models.request;

        import com.camaradevelopment.media.service.models.media.Media;

        import javax.net.ssl.SSLSession;
        import java.net.URI;
        import java.net.http.HttpClient;
        import java.net.http.HttpHeaders;
        import java.net.http.HttpRequest;
        import java.net.http.HttpResponse;
        import java.util.Optional;

public class MediaOkResponse implements HttpResponse<Media> {

    private Media media;

    public MediaOkResponse(Media media) {
        this.media = media;
    }

    @Override
    public int statusCode() {
        return 200;
    }

    @Override
    public HttpRequest request() {
        return null;
    }



    @Override
    public Optional<HttpResponse<Media>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @Override
    public Media body() {
        return this.media;
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
