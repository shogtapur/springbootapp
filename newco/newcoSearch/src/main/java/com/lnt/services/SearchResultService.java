package com.lnt.services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lnt.services.data.Contents;
import com.lnt.services.data.Header;
import com.lnt.services.data.ProjectDetails;
import com.lnt.services.data.RootJson;
import com.lnt.services.youtube.YouTubePoster;
import com.lnt.workers.WorkerManger;

@Service
public class SearchResultService {

	private String searchString;

	private Map<String, String[]> map;
	private String urlFromProps;
	private String searchUrl;
	private CountDownLatch countDownLatch;
	private Contents[] contents;

	@Autowired
	private YouTubePoster poster;

	@Autowired
	public ProjectDetails bean;

	@Autowired
	private WorkerManger workerManger;

	private RestTemplate restTemplate;

	public SearchResultService(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	public RootJson getSearchResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		RootJson json = new RootJson();
		String url = httpServletRequest.getRequestURI();
		map = httpServletRequest.getParameterMap();
		String qString = httpServletRequest.getQueryString();
		StringBuffer urlString = httpServletRequest.getRequestURL();
		System.out.println("Query String " + qString);
		System.out.println("Requestd Url " + urlString);
		System.out.println("Request URI " + url);
		urlFromProps = "http://" + bean.getCmdcIpAddress() + ":" + bean.getCmdcServerPortNumber() + "/cmdc/content";
		System.out.println("CMDC URL from props " + urlFromProps);
		urlString = new StringBuffer(urlFromProps);
		searchUrl = getSearchURL(urlString, qString);
		System.out.println("search Url  " + searchUrl);
		searchString = getSearchString(map);
		System.out.println("search string " + searchString);
		workerManger.exectureWorker(searchString, searchUrl, restTemplate);
		System.out.println("SearchResultService.getSearchResult() get result from worker ");
		
		RootJson yJson = workerManger.getyJson();
		RootJson cJson = workerManger.getcJson();
		contents = yJson.getContents();
		System.out.println("SearchMicroServices.getSearchResult() content length " + contents.length);
		poster.updateMediaURIForYoutube(contents);
		json = getCompleteResult(yJson, cJson);
		return json;

	}

	private String getSearchURL(StringBuffer url, String queryString) {
		String searchUrl = null;

		if (queryString != null)
			searchUrl = url + "?" + queryString;
		else
			searchUrl = new String(url);

		return searchUrl;
	}

	private String getSearchString(Map map) {
		String searchString = null;
		Set s = map.entrySet();
		Iterator it = s.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();
			String key = entry.getKey();
			String[] value = entry.getValue();
			// System.out.println("Key is " + key + " -->" + value);

			for (int j = 0; j < value.length; j++) {
				// System.out.println("Q value " + value[j]);
				if (value[j].contains("sortTitle")) {
					String[] str = value[j].split(",");
					// System.out.println("su " + str.length);
					if (str.length > 1) {
						for (int i = 0; i < str.length; i++) {
							// System.out.println("Sunil " + str[i]);
							if (str[i].contains("sortTitle~")) {
								searchString = str[i].substring(str[i].indexOf('~') + 1);
								System.out.println("---");
							}
						}
					}

				}
			}
		}
		return searchString;

	}

	private RootJson getCompleteResult(RootJson youtubResult, RootJson cmdcResult) {
		RootJson finalJson = new RootJson();
		if (youtubResult != null && cmdcResult != null) {
			Header commmonHeader = new Header();
			commmonHeader.setTotal(cmdcResult.getHeader().getTotal() + youtubResult.getHeader().getTotal());
			String commonError = cmdcResult.getError();
			int cLen = cmdcResult.getContents().length;
			int yLen = youtubResult.getContents().length;
			System.out.println(" cLen " + cLen + " yLen " + yLen);
			Contents contents[] = new Contents[cLen + yLen];
			System.arraycopy(youtubResult.getContents(), 0, contents, 0, yLen);
			System.arraycopy(cmdcResult.getContents(), 0, contents, yLen, cLen);
			System.out.println(" $$$$$$$$$$$  " + Arrays.toString(contents));

			finalJson.setFacets(null);
			finalJson.setContents(contents);
			finalJson.setError(commonError);
			finalJson.setHeader(commmonHeader);
			System.out.println("##########################################################################3");
			System.out.println(finalJson);
			return finalJson;
		} else if (youtubResult == null && cmdcResult != null) {
			return finalJson = cmdcResult;
		} else if (cmdcResult == null && youtubResult != null) {
			return finalJson = youtubResult;
		}
		return finalJson;
	}

}
