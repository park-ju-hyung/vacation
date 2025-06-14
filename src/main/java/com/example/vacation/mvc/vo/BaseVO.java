package com.example.vacation.mvc.vo;

public class BaseVO {
	private Long seq;
	private int message;
	private int fileYn;
	
	private long prevId;
	private String prevTitle;
	
	private long nextId;
	private String nextTitle;
	
	public BaseVO() {
		
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int getFileYn() {
		return fileYn;
	}

	public void setFileYn(int fileYn) {
		this.fileYn = fileYn;
	}

	public long getPrevId() {
		return prevId;
	}

	public void setPrevId(long prevId) {
		this.prevId = prevId;
	}

	public String getPrevTitle() {
		return prevTitle;
	}

	public void setPrevTitle(String prevTitle) {
		this.prevTitle = prevTitle;
	}

	public long getNextId() {
		return nextId;
	}

	public void setNextId(long nextId) {
		this.nextId = nextId;
	}

	public String getNextTitle() {
		return nextTitle;
	}

	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}
	
}
