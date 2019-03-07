package com.lnt.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatlogIdService {

	private RestTemplate restTemplate;

	public CatlogIdService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getCatlogId() {

		String catlogid = null;
		String url = "http://10.126.53.144:8085/cmdc/region/24833/catalogueId?version=HD_PDL";
		String string = null;
		URI uri2 = null;

		try {
			uri2 = new URI(url);
			System.out.println(" CatlogIdService " + uri2.isAbsolute());
			System.out.println(" CatlogIdService cmdc URI  " + uri2);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		try {
			catlogid = restTemplate.getForObject(uri2, String.class);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@  CMDC  @@@@@@@@@@@@@@@@@@@@@@2");
			System.out.println(string);
		} catch (Exception e) {
			e.printStackTrace();
			string = null;

		}

		return catlogid;

	}

}
