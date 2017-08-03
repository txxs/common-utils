package com.txxs.common.utils.httpclient;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;

/**
 * @Author:jianghuimin
 * @Date: 2017/8/3
 * @Time:10:23
 */
public class StringHttpResponse {
    CloseableHttpResponse httpResponse;
    private String content = null;
    private long cost = 0L;

    public StringHttpResponse(CloseableHttpResponse httpResponse, long cost) {
        this.httpResponse = httpResponse;
        this.cost = cost;
    }

    public String content() throws IOException {
        if(this.content == null) {
            HttpEntity entity = this.httpResponse.getEntity();

            try {
                this.content = EntityUtils.toString(entity, Consts.UTF_8);
            } finally {
                this.close();
            }
        }

        return this.content;
    }

    public void close() throws IOException {
        this.httpResponse.close();
    }

    public long getCost() {
        return this.cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public StatusLine getStatusLine() {
        return this.httpResponse.getStatusLine();
    }

    public HttpEntity getEntity() {
        return this.httpResponse.getEntity();
    }

    public Locale getLocale() {
        return this.httpResponse.getLocale();
    }

    public ProtocolVersion getProtocolVersion() {
        return this.httpResponse.getProtocolVersion();
    }

    public boolean containsHeader(String name) {
        return this.httpResponse.containsHeader(name);
    }

    public Header[] getHeaders(String name) {
        return this.httpResponse.getHeaders(name);
    }

    public Header getFirstHeader(String name) {
        return this.httpResponse.getFirstHeader(name);
    }

    public Header getLastHeader(String name) {
        return this.httpResponse.getLastHeader(name);
    }

    public Header[] getAllHeaders() {
        return this.httpResponse.getAllHeaders();
    }

    public HeaderIterator headerIterator() {
        return this.httpResponse.headerIterator();
    }

    public HeaderIterator headerIterator(String name) {
        return this.httpResponse.headerIterator(name);
    }
}

