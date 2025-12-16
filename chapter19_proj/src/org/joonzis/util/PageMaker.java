package org.joonzis.util;

public class PageMaker {



	    private int page;          // 현재 페이지
	    private int totalCount;    // 전체 게시글 수
	    private int perPage;       // 페이지당 게시글 수

	    private int startPage;     // 시작 페이지 번호
	    private int endPage;       // 끝 페이지 번호

	    private boolean prev;      // 이전 버튼 여부
	    private boolean next;      // 다음 버튼 여부

	    // 페이지 버튼 개수 (ex: 1 2 3 4 5)
	    private static final int DISPLAY_PAGE_NUM = 5;

	    /**
	     * PageMaker 생성자
	     * @param page 현재 페이지
	     * @param totalCount 전체 게시글 수
	     * @param perPage 페이지당 게시글 수
	     */
	    public PageMaker(int page, int totalCount, int perPage) {
	        this.page = page;
	        this.totalCount = totalCount;
	        this.perPage = perPage;

	        calcPaging();
	    }

	    /**
	     * 페이지네이션 계산
	     */
	    private void calcPaging() {

	        // 끝 페이지 계산
	        endPage = (int) Math.ceil(page / (double) DISPLAY_PAGE_NUM) * DISPLAY_PAGE_NUM;

	        // 시작 페이지 계산
	        startPage = endPage - DISPLAY_PAGE_NUM + 1;

	        // 실제 마지막 페이지
	        int realEnd = (int) Math.ceil(totalCount / (double) perPage);

	        // 끝 페이지 보정
	        if (endPage > realEnd) {
	            endPage = realEnd;
	        }

	        // 이전/다음 버튼 여부
	        prev = startPage > 1;
	        next = endPage < realEnd;
	    }

	    // ===== getter =====
	    public int getPage() {
	        return page;
	    }

	    public int getStartPage() {
	        return startPage;
	    }

	    public int getEndPage() {
	        return endPage;
	    }

	    public boolean isPrev() {
	        return prev;
	    }

	    public boolean isNext() {
	        return next;
	    }
}


