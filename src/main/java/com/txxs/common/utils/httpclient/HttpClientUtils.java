package com.txxs.common.utils.httpclient;

import com.txxs.common.utils.common.NameValueList;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Author:jianghuimin
 * @Date: 2017/8/3
 * @Time:10:21
 */
public class HttpClientUtils {

    public HttpClientUtils() {
    }

    private static CloseableHttpClient httpclient=null;

    static {
        RequestConfig config = RequestConfig.custom().build();
        httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
    }

    public static StringHttpResponse post(HttpHost host, URI uri, Header[] headers, String json) throws IOException {
        long t = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost(host.toString().concat(uri.toString()));
        httpPost.setHeaders(headers);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        httpPost.setHeader(new BasicHeader("Accept", "application/json"));
        httpPost.setEntity(new StringEntity(json, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse post(String url, Header[] headers, String json) throws IOException {
        long t = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(headers);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        httpPost.setHeader(new BasicHeader("Accept", "application/json"));
        httpPost.setEntity(new StringEntity(json, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse put(HttpHost host, URI uri, Header[] headers, String json) throws IOException {
        long t = System.currentTimeMillis();
        HttpPut httpPut = new HttpPut(host.toString().concat(uri.toString()));
        httpPut.setHeaders(headers);
        httpPut.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        httpPut.setHeader(new BasicHeader("Accept", "application/json"));
        httpPut.setEntity(new StringEntity(json, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(httpPut);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse put(String url, Header[] headers, String json) throws IOException {
        long t = System.currentTimeMillis();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeaders(headers);
        httpPut.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        httpPut.setHeader(new BasicHeader("Accept", "application/json"));
        httpPut.setEntity(new StringEntity(json, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(httpPut);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse get(HttpHost host, URI uri, Header[] headers, NameValueList<String, String> params) throws IOException {
        long t = System.currentTimeMillis();
        String queryString = EntityUtils.toString(new UrlEncodedFormEntity(parse(params), Consts.UTF_8));
        HttpGet httpGet = new HttpGet(host.toString().concat(uri.toString()).concat("?").concat(queryString));
        httpGet.setHeaders(headers);
        httpGet.setHeader(new BasicHeader("Accept", "application/json"));
        CloseableHttpResponse response = httpclient.execute(httpGet);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse get(String url, Header[] headers, NameValueList<String, String> params) throws IOException {
        long t = System.currentTimeMillis();
        String queryString = EntityUtils.toString(new UrlEncodedFormEntity(parse(params), Consts.UTF_8));
        HttpGet httpGet = new HttpGet(url.concat("?").concat(queryString));
        httpGet.setHeaders(headers);
        httpGet.setHeader(new BasicHeader("Accept", "application/json"));
        CloseableHttpResponse response = httpclient.execute(httpGet);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse delete(HttpHost host, URI uri, Header[] headers, NameValueList<String, String> params) throws IOException {
        long t = System.currentTimeMillis();
        String queryString = EntityUtils.toString(new UrlEncodedFormEntity(parse(params), Consts.UTF_8));
        HttpDelete httpDelete = new HttpDelete(host.toString().concat(uri.toString()).concat("?").concat(queryString));
        httpDelete.setHeaders(headers);
        httpDelete.setHeader(new BasicHeader("Accept", "application/json"));
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static StringHttpResponse delete(String url, Header[] headers, NameValueList<String, String> params) throws IOException {
        long t = System.currentTimeMillis();
        String queryString = EntityUtils.toString(new UrlEncodedFormEntity(parse(params), Consts.UTF_8));
        HttpDelete httpDelete = new HttpDelete(url.concat("?").concat(queryString));
        httpDelete.setHeaders(headers);
        httpDelete.setHeader(new BasicHeader("Accept", "application/json"));
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        return new StringHttpResponse(response, System.currentTimeMillis() - t);
    }

    public static List<NameValuePair> parse(NameValueList<String, String> nameValueList) {
        return (List)(nameValueList == null?new ArrayList():(List)nameValueList.getList().stream().map((nvp)-> {
                        return new BasicNameValuePair((String)nvp.getName(), (String)nvp.getValue());}).collect(Collectors.toList()));
    }
}

