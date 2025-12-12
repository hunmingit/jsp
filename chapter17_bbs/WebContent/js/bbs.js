let pageNum = new URLSearchParams(location.search).get("pageNum");
//게시글 삽입 페이지 이동 함수
function moveInsertPage(){
    location.href='BBSController?cmd=insertBBSPage';
}
//목록으로 이동하는 함수 
//왜 뒤로가기 안하냐 뒤로가기로 가면 그사이에 누가 작성한 글이 최신화가 안됨 그리고 링크로 들어갔을 때 목록으로 안가짐
function view_all(){
    location.href='BBSController?cmd=allList&pageNum='+pageNum+'&amount=5';  
}
//게시글 등록 작성자, 제목, 내용 필수 검증 후 전송
function insert(f){
			if(f.title.value == '' ||f.writer.value == '' ||f.content.value == '' ){
			alert("필수 내용(작성자, 제목, 내용)을 입력하세요");
			return;
		}
		f.action = 'BBSController'
		f.submit();
}
//게시글 수정 페이지 이동
function updatePage(){
	location.href='BBSController?cmd=updatePage&pageNum='+pageNum+'&amount=5';
}
//게시글 수정 함수
function update(f){
	if(f.title.value == '' ||f.writer.value == '' ||f.content.value == '' ){
		alert("필수 내용(작성자, 제목, 내용)을 입력하세요");
		return;
	}
	f.action = 'BBSController';
	f.submit();
}
//게시글 삭제 함수
function removeBBS(b_idx){
	//컨트롤로 전달 할 파라미터 == b.idx /cmd
	//서비스의 메소드 이름 removeBBS
	//매퍼 id delete_bbs
	//삭제 완료 후 allList로 이동
	if(confirm("해당 게시글을 삭제하시겠습니까?")){
		alert("게시글 번호 : " + b_idx + "번이 삭제되었습니다.");
		location.href='BBSController?cmd=removeBBS&b_idx='+b_idx+'&pageNum='+pageNum;
	}else{
			alert("삭제가 취소되었습니다.");
			return;
		}
}

//페이지 버튼 클릭 이벤트
//page-nation 클래스 내부의 li 태그 내부의 a 태그를 모두 선택
//for문을 돌면서 a 를 하나씩 가져옴
//aEle에 a태그들 담기 
document.querySelectorAll('.page-nation li a').forEach(aEle => {
	aEle.addEventListener('click', (e) => {
		e.preventDefault(); //기본 이벤트 막기

		//a 태그의 href 속성값(누르는 page) 을 가져옴
		let pageNum = aEle.getAttribute('href') //let pageNum = e.currentTarget.getAttribute('href') 도 가능
		let sendData = 'cmd=allList&pageNum='+pageNum+'&amount=5';
		location.href = 'BBSController?' + sendData;
	});
});

