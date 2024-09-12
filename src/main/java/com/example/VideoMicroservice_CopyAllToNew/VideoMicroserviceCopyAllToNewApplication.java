package com.example.VideoMicroservice_CopyAllToNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VideoMicroserviceCopyAllToNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoMicroserviceCopyAllToNewApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
