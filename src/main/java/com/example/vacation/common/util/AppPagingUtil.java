package com.example.vacation.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppPagingUtil {
	// 페이징 전체 페이지 번호
	public static int getTotalPageNo(int totalCount, int pageSize) {
		int totalPageNo = 1;
		if (totalCount > 0 && pageSize > 0) {
			totalPageNo = (int) Math.ceil((double) totalCount / pageSize);
		}
		return totalPageNo;
	}

	// 페이징 offset(시작 번호) 구하기
	public static int getOffset(int pageNo, int pageSize) {
		return pageSize * (pageNo - 1);
	}

	// 페이징 끝 번호 구하기
	public static int getEndNo(int pageNo, int pageSize) {
		return getOffset(pageNo, pageSize) + pageSize;
	}

	// 관리자 페이징 html
	public static String getMngrPagingHtml(int totalCount, int pageNo, int pageSize, int pageBlock) {

		StringBuffer rs = new StringBuffer();

		long totalPage = getTotalPageNo(totalCount, pageSize);

		if (totalCount > 0) {
			// 현재 페이지 계산
			long startPage = 1;
			if (pageNo > 0 && pageBlock > 0) {
				if (pageNo % pageBlock > 0) {
					startPage = pageNo - (pageNo % pageBlock) + 1;
				} else {
					startPage = pageNo - pageBlock + 1;
				}
			}

			// 마지막 페이지 계산
			long endPage = startPage + pageBlock - 1;
			if (endPage > totalPage) {
				endPage = totalPage; // 마지막 페이지가 총 페이지 수 보다 크면 총 페이지로 셋팅
			}

			// 맨처음, 이전 글
			long beforePage = startPage - pageBlock;
			if (beforePage < 1) {
				beforePage = 1;
			}

			// 맨마지막, 다음 글
			long afterPage = startPage + pageBlock;
			if (afterPage > totalPage) {
				afterPage = totalPage;
			}
			
			String prevActive = "";
			if (beforePage == 1) {
				prevActive = "disabled";
			}

			String nextActive = "";
			if ((startPage + pageBlock) > totalPage) {
				nextActive = "disabled";
			}
			
			rs.setLength(0);

			rs.append("<div class=\"pageNo-group\">");

			// 이전
			rs.append("<span class=\"pageNo prev\" data-pageNo=\"" + beforePage + "\"><</span>");
			// 이동 페이지
			for (long i = startPage; i <= endPage; i++) {
				if (pageNo == i) {
					rs.append("<span class=\"pageNo pg-active\">"+ i +"</span>");
				} else {
					rs.append("<span class=\"pageNo\" data-pageNo=\""+ i +"\">"+ i +"</span>");
				}
			}

			// 다음
			rs.append("<span class=\"pageNo next\" data-pageNo=\""+ afterPage +"\">></span>");
			rs.append("</div>");
			
			/*
			<div class="pageNo-group">
				<span class="prev">이전</span>
				<span class="pageNo pg-active">1</span>
				<span class="pageNo">2</span>
				<span class="pageNo">3</span>
				<span class="pageNo">4</span>
				<span class="pageNo">5</span>
				<span class="pageNo">6</span>
				<span class="pageNo">7</span>
				<span class="pageNo">8</span>
				<span class="pageNo">9</span>
				<span class="pageNo">10</span>
				<span class="next">다음</span>
			</div> */
			
		}

		return rs.toString();
	}

	// 사이트 페이징 html
	public static String getSitePagingHtml(int totalCount, int pageNo, int pageSize, int pageBlock) {

		StringBuffer rs = new StringBuffer();

		int totalPage = getTotalPageNo(totalCount, pageSize);

		if (totalCount > 0) {
			// 현재 페이지 계산
			long startPage = 1;
			if (pageNo > 0 && pageBlock > 0) {
				if (pageNo % pageBlock > 0) {
					startPage = pageNo - (pageNo % pageBlock) + 1;
				} else {
					startPage = pageNo - pageBlock + 1;
				}
			}

			// 마지막 페이지 계산
			long endPage = startPage + pageBlock - 1;
			if (endPage > totalPage) {
				endPage = totalPage; // 마지막 페이지가 총 페이지 수 보다 크면 총 페이지로 셋팅
			}

			// 맨처음, 이전 글
			long beforePage = startPage - pageBlock;
			if (beforePage < 1) {
				beforePage = 1;
			}

			// 맨마지막, 다음 글
			long afterPage = startPage + pageBlock;
			if (afterPage > totalPage) {
				afterPage = totalPage;
			}
			
			String prevActive = "";
			if (beforePage == 1) {
				prevActive = "disabled";
			}

			String nextActive = "";
			if ((startPage + pageBlock) > totalPage) {
				nextActive = "disabled";
			}

			rs.setLength(0);

			rs.append("<ul class=\"pagination\">\n");

			// 맨처음
			// rs.append("	<a class=\"page-link pprev\" href=\"#\" data-pageNo=\"1\">처음</a>");

			// 이전
			rs.append("<li class=\"page-item previous\">");
			rs.append("<span class=\"page-link pointer\" data-pageNo=\""+ beforePage +"\"><i class=\"xi-angle-left\"></i></span>");
			rs.append("</li>");
			
			// 이동 페이지
			for (long i = startPage; i <= endPage; i++) {
				rs.append("<li class=\"page-item\">");
				if (pageNo == i) {
					rs.append("	<span class=\"page-link choice\">" + i + "</span>");
				} else {
					rs.append("	<span class=\"page-link pointer\" data-pageNo=\"" + i + "\">" + i + "</span>");
				}
				rs.append("</li>");
			}

			// 다음
			rs.append("<li class=\"page-item next\">");
			rs.append("<span class=\"page-link pointer\" data-pageNo=\""+ afterPage +"\">"
					+ "<i class=\"xi-angle-right\" data-pageNo=\""+ afterPage +"\"></i>");
			rs.append("</span>");
			rs.append("</li>");
			
			//rs.append("<a href=\"#\" class=\"page-link next " + prevActive + "\" data-pageNo=\"" + afterPage + "\">다음</a> ");

			// 마지막
			//rs.append("<a class=\"page-link next\" href=\"#\" data-pageNo=\""+totalPage+"\"></a>");

			rs.append("</ul>");
		}
		
		/*
			<ul class="pagination">
				<li class="page-item previous">
					<span class="page-link" data-pageNo="">◁</span>
				</li>			
				<li class="page-item">
					<span class="page-link" data-pageNo="">1</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">2</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">3</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">4</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">5</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">6</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">7</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">8</span>
				</li>
				<li class="page-item">
					<span class="page-link" data-pageNo="">9</span>
				</li>				
				<li class="page-item">
					<span class="page-link" data-pageNo="">10</span>
				</li>				
				<li class="page-item next">
					<span class="page-link" data-pageNo="">▷</span>
				</li>			
			</ul>
		 */

		return rs.toString();
	}
}
