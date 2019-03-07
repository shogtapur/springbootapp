package com.lnt.workers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.springframework.web.client.RestTemplate;

import com.lnt.services.data.RootJson;

public class CMDCWorker implements Callable<RootJson> {

	private RestTemplate restTemplate;
	private String searchUrl;
	private CountDownLatch countDownLatch;

	public CMDCWorker(String searchUrl, RestTemplate restTemplate, CountDownLatch countDownLatch) {
		this.restTemplate = restTemplate;
		this.searchUrl = searchUrl;
		this.countDownLatch = countDownLatch;
	}

	public RootJson call() {
		RootJson cmdcResult = null;
		URI uri2 = null;
		System.out.println("CMDCWorker.call() search url  for CMDC " + searchUrl);
		if (searchUrl != null) {
			try {
				uri2 = new URI(searchUrl);
				System.out.println(uri2.isAbsolute());
				System.out.println("cmdc URI  " + uri2);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@  CMDC  @@@@@@@@@@@@@@@@@@@@@@2");
				// cmdcResult = restTemplate.getForObject(uri2, RootJson.class);
			} catch (Exception e) {
				System.out.println("CMDCWorker.call()" + e.getMessage());

			} finally {
				countDownLatch.countDown();
				System.out.println("CMDCWorker.call() latch count " + countDownLatch.getCount());
			}
		}
		return cmdcResult;
	}

}
