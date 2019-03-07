package com.lnt.services.youtube;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.lnt.services.data.Contents;
import com.lnt.services.data.Media;

@Service
public class YouTubePoster {
	private HashMap<String, String> urlMapping = new HashMap<>();

	public YouTubePoster() {
		System.out.println("YouTubePoster.YouTubePoster()");
	}

	public HttpEntity<byte[]> getImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Contents[] contents) throws IOException {
		StringBuffer urlString = httpServletRequest.getRequestURL();
		System.out.println("YouTubePoster.getImage() url string" + urlString);
		String yUrl = urlMapping.get(new String(urlString));
		System.out.println("YouTubePoster.getImage() youtube url " + yUrl);
		URL url = new URL(yUrl);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] image = out.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}

	public void updateMediaURIForYoutube(Contents[] contents) {

		for (int i = 0; i < contents.length; i++) {
			Media media[] = contents[i].getMedia();
			for (int j = 0; j < media.length; j++) {
				System.out.println(" Media url " + media[j].getUri());
				String url = storeUrl(media[j].getUri());
				media[j].setUri(url);
			}

		}

	}

	private String storeUrl(String url) {

		String localUrl = "http://localhost:8080/image";
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("YouTubePoster.storeUrl()  host address " + ip.getHostAddress());
			localUrl = "http://" + ip.getHostAddress() + ":8080/image";
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		System.out.println("orginal url " + url);
		try {
			URL url1 = new URL(url);
			localUrl = localUrl + url1.getPath();
			System.out.println("YouTubePoster.storeUrl() " + localUrl);
			urlMapping.put(localUrl, url);
			return localUrl;
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		return null;
	}

}
