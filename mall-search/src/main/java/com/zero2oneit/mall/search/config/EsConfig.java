/*
package com.zero2oneit.mall.search.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

*/
/**
 * Description:
 *
 * @author Lee
 * @date 2021/3/17 13:16
 *//*

public class EsConfig extends AbstractElasticsearchConfiguration {

    */
/*@Autowired
    private EsProperties prop;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        HttpHost host = new HttpHost(prop.getHost(), prop.getPort(), HttpHost.DEFAULT_SCHEME_NAME);
        RestClientBuilder builder=RestClient.builder(host);
        //如果设置了密码则打开
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(prop.getUsername(), prop.getPassword()));
        builder.setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }*//*


}
*/
