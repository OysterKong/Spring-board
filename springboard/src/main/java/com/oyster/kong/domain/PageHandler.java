package com.oyster.kong.domain;

import java.util.Iterator;

public class PageHandler {
	
	int totalCnt; // 총 게시물 갯수
	int pageSize; // 한 페이지의 크기
	int naviSize = 10; // 페이지 네비게이션의 크기 = 10개로 고정
	int totalPage; // 전체 페이지의 갯수
	int page; // 현재 페이지
	int beginPage; // 내비게이션의 첫번째 페이지
	int endPage; // 내비게이션의 마지막 페이지
	boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄 것인지 여부
	boolean showNext; // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부
	
	
	// totalCnt 와 page만 받은 경우 pageSize를 10으로 하게끔 설정
	public PageHandler(int totalCnt, int page) {
		this(totalCnt, page, 10);
	}
	
	
	//페이지 처리를 하는데 1. 총게시물이 몇개인지, 현재 페이지가 어디인지, 한페이지가 몇개인지가 필요
	public PageHandler(int totalCnt, int page, int pageSize) {
		this.totalCnt = totalCnt;
		this.page = page;
		this.pageSize = pageSize;
		
		totalPage = (int) Math.ceil(totalCnt / pageSize);
		beginPage = page / naviSize * naviSize + 1;
		endPage = Math.min(beginPage + naviSize, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
	}
	
	void print() {
		System.out.println("page = " + page);
		System.out.println(showPrev ? "[PREV] " : "");
		for(int i = beginPage; i <= endPage; i++) {
			System.out.println(i + " ");
		}
		System.out.println(showNext ? " [NEXT]" : "");
	}

	@Override
	public String toString() {
		return "PageHandler [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize
				+ ", totalPage=" + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", showPrev=" + showPrev + ", showNext=" + showNext + "]";
	}
	
	

}
