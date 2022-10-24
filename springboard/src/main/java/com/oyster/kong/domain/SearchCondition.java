package com.oyster.kong.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
	
	private Integer page = 1;
	private Integer pageSize = 10;
//	private Integer offset = 0;
	private String keyword = "";
	private String option = "";
	

	
	public SearchCondition() {}
	
	public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.option = option;
	}
	
	public String getQueryString(Integer page) { // 지정된 페이지를 매개변수로 받아서 사용하는 메서드
		// ?page=1&pageSize=10&option=T&keyword="title"  이 형태로 queryString을 자동으로 만들어주는 메서드
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("page", pageSize)
				.queryParam("page", option)
				.queryParam("page", keyword)
				.build().toString();
	}
	
	
	public String getQueryString() {
		return getQueryString(page);
	}


	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getOffset() {
		return (page-1) * pageSize;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "SearchCondition [page=" + page + ", pageSize=" + pageSize + ", offset=" + getOffset() + ", keyword="
				+ keyword + ", option=" + option + "]";
	}
	
	

}
