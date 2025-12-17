function deletePost(bIdx) {
	//컨트롤로 전달 할 파라미터 == bIdx /cmd
	//서비스의 메소드 이름 removeBBS
	//매퍼 id delete_bbs
	//삭제 완료 후 allList로 이동
	if(confirm("해당 게시글을 삭제하시겠습니까?")){
		alert("게시글 번호 : " + bIdx + "번이 삭제되었습니다.");
		location.href="ProjController?cmd=delete&bIdx=" + bIdx;
	}else{
			alert("삭제가 취소되었습니다.");
			return;
		}
}




