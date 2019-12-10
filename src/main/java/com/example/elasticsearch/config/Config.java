package com.example.elasticsearch.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class Config {
	
	@Value("${elastic.search.home}")
    private String elasticsearchHome;
	
	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${elastic.search.port}")
	private String serverPort;
	
	@Value("${elastic.search.hostname}")
	private String hostname;
	


	@Bean
	public Client client() {
		Settings elasticsearchSettings = Settings.builder().put("client.transport.sniff", true)
				.put("path.home", elasticsearchHome).put("cluster.name", clusterName).build();
		TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
		try {
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(hostname), Integer.parseInt(serverPort)));
		} catch (UnknownHostException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}
}