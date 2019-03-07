package com.lnt.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnt.services.data.Contents;
import com.lnt.services.data.ProjectDetails;
import com.lnt.services.data.RootJson;
import com.lnt.services.youtube.YouTubePoster;

@RestController
@ComponentScan("com.lnt")
public class SearchMicroServices {

	@Autowired
	public ProjectDetails bean;
	@Autowired
	private YouTubePoster poster;
	@Autowired
	private CatlogIdService catlogIdService;
	@Autowired
	private SearchResultService resultService;
	private Contents[] contents;
	private String urlFromProps;

	public SearchMicroServices() {
	}

	@RequestMapping("projectName")
	String getProjectName() {
		urlFromProps = "http://" + bean.getCmdcIpAddress() + ":" + bean.getCmdcServerPortNumber() + "/cmdc/content";
		System.out.println(urlFromProps);
		return bean.getProjectName() + " CMDC server Ip " + bean.getCmdcIpAddress() + " and port number  "
				+ bean.getCmdcServerPortNumber();
	}

	@RequestMapping("/cmdc/region/24833/catalogueId")
	public String getCatlogId() {
		return catlogIdService.getCatlogId();
	}

	@RequestMapping("image/*/*/*")
	public HttpEntity<byte[]> getImage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {
		return this.poster.getImage(httpServletRequest, httpServletResponse, contents);

	}

	@RequestMapping("cmdc/content")
	public RootJson getSearchResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return this.resultService.getSearchResult(httpServletRequest, httpServletResponse);
	}

}
