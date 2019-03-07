package com.lnt.workers;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.springframework.web.client.RestTemplate;

import com.lnt.services.data.RootJson;

public class YouTubeWorker implements Callable<RootJson> {

	private RestTemplate restTemplate;
	private String searchString;
	private CountDownLatch countDownLatch;

	public YouTubeWorker(String searchString, RestTemplate restTemplate, CountDownLatch countDownLatch) {
		this.restTemplate = restTemplate;
		this.searchString = searchString;
		this.countDownLatch = countDownLatch;
	}

	public RootJson call() {
		String uri = null;
		if (searchString != null)
			uri = "http://localhost:8090/searchYouTube/" + searchString;
		else
			uri = "http://localhost:8090/searchYouTube";

		System.out.println("search URl for YouTube " + uri);
		RootJson youtubResult = null;
		try {
			System.out.println("YouTubeWorker.call()");
			System.out.println("@@@@@@@@@@@@@@@ YOUTUBE @@@@@@@@@@@@@@@@@@@@");
			youtubResult = restTemplate.getForObject(uri, RootJson.class);
		} catch (Exception e) {
			System.out.println("YouTubeWorker.call()"+e.getMessage()); 
		} finally {
			countDownLatch.countDown();
			System.out.println("YouTubeWorker.call() latch count " + countDownLatch.getCount());
		}
		return youtubResult;
	}

}
