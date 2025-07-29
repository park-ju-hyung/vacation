package com.example.vacation.mvc.dto;

import java.util.List;

public class BaseDTO {
	//페이징
	private int pageNo = 1;//페이지 번호
	private int pageSize = 10;//페이지 사이즈
	private int pageBlock = 10;//페이지 블록
	private int pageOffset;//페이징 범위

	//검색
	private String searchField;//검색 필드
	private String searchKwd;//검색 단어
	private String schDtStart;
	private String schDtEnd;

	private String schDtStart2;
	private String schDtEnd2;
	
	// 첨부파일 키
	private List<String> fileKeys;
	// 게시글 순번
	private Long seq;
	
	public BaseDTO() {

	}

	
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
	public String getSearchKwd() {
		return searchKwd;
	}
	public void setSearchKwd(String searchKwd) {
		this.searchKwd = searchKwd;
	}
	
	public String getSchDtStart() {
		return schDtStart;
	}

	public String getSchDtStart2() {
		return schDtStart2;
	}
	public void setSchDtStart(String schDtStart) {
		this.schDtStart = schDtStart;
	}

	public String getSchDtEnd() {
		return schDtEnd;
	}
	public String getSchDtEnd2() {
		return schDtEnd2;
	}
	public void setSchDtEnd(String schDtEnd) {
		this.schDtEnd = schDtEnd;
	}

	public List<String> getFileKeys() {
		return fileKeys;
	}
	public void setFileKeys(List<String> fileKeys) {
		this.fileKeys = fileKeys;
	}
	
	
}
