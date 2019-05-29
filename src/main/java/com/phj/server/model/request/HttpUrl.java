package com.phj.server.model.request;



public class HttpUrl {


    /** Either "http" or "https". */
    final String scheme;

    /** Canonical hostname. */
    final String host;

    /** Either 80, 443 or a user-specified port. In range [1..65535]. */
    final int port;


    /** Canonical URL. */
    private final String url;


    public HttpUrl(String scheme, String host, int port, String url) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.url = url;
    }

    public String getScheme() {
        return scheme;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "HttpUrl{" +
                "scheme='" + scheme + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                '}';
    }
}

