package com.example.elasticsearch.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.baeldung.spring.data.es.repository")
@ComponentScan(basePackages = { "com.baeldung.spring.data.es.service" })
public class Config {
	@org.springframework.beans.factory.annotation.Value("C:\\Vinod\\Softwares\\elasticsearch-6.4.0\\elasticsearch-6.4.0")
	private String elasticsearchHome;
	@org.springframework.beans.factory.annotation.Value("my-application")
	private String clusterName;

	@Bean
	public Client client() {
		Settings elasticsearchSettings = Settings.builder().put("client.transport.sniff", true)
				.put("path.home", elasticsearchHome).put("cluster.name", clusterName).build();
		TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
		try {
			client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}
}