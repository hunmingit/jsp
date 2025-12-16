//HTML에 있는 요소들을 JS에서 조작하기 위해 가져옴
const loginTab = document.getElementById("loginTab");
const joinTab = document.getElementById("joinTab");
const pId = document.getElementById("pId");
const pPw = document.getElementById("pPw");
const cmd = document.getElementById("cmd");
const pPwRe = document.getElementById("pPwRe");
const idCkBtn = document.getElementById("idCkBtn");

let idCk=false; // 아이디 중복 여부 확인 

// 기본: 로그인 탭 
let isLogin = true;

// 로그인 탭 클릭 시
loginTab.onclick = () => { 
	if(!isLogin){
		pId.value = ""; //기존 회원가입 입력값 초기화
		pPw.value = "";
		pPwRe.value = "";		
	}
  isLogin = true; //로그인 탭으로 이동

//css용 선택된 탭 강조 표시
  loginTab.classList.add("active");
  joinTab.classList.remove("active");

  idCkBtn.style.display = "none";//중복확인 버튼 숨기기
  pPwRe.style.display = "none";   // 비밀번호 확인 숨기기
  cmd.value = "login"; //로그인 탭일 때 cmd value = login
  loginSubBtn.textContent = "로그인"; 
  adminInfo.style.display = "block"; //어드민 정보 보이기 
};

// 회원가입 탭 클릭
joinTab.onclick = () => {
 	 if(isLogin){
		pId.value = "";
		pPw.value = "";
		pPwRe.value = "";		
	}
  isLogin = false;//회원가입 탭으로 이동

//css용 선택된 탭 강조 표시
  joinTab.classList.add("active");
  loginTab.classList.remove("active");

  idCkBtn.style.display = "block";//중복확인 버튼 보이기
  pPwRe.style.display = "block";  // 비밀번호 확인 보이기
  cmd.value = "join";
  loginSubBtn.textContent = "회원가입";
  adminInfo.style.display = "none"; //어드민 정보 숨기기
};

//로그인/회원가입 버튼 클릭 분기
loginSubBtn.addEventListener('click', ()=>{
		if(isLogin){			
				login();
		}else{
				join();	
		}										
	});

//로그인 함수
function login(){
	//공백 제거 후 값 가져오기(trim)
	const id = pId.value.trim();
	const pw = pPw.value.trim();
	
  	if(id === "" || pw === "") {
    	alert("모든 항목을 입력하세요.");
	 	return;
	}
//document : JS가 HTML 요소에 접근할 수 있게 해주는 객체	
//이페이지(html) 안에서 id=f 인 요소를 찾아라  
	document.getElementById("f").submit();
	
}

// 아이디 중복확인 버튼
idCkBtn.addEventListener("click", () => {
  const id = pId.value.trim();

  if(id === "") {
    alert("아이디를 입력하세요.");
    pId.focus();
    return;
  }
//아이디 정규식 영문/숫자 4~12
  const idReg = /^[a-zA-Z0-9]{4,12}$/;
  if(!idReg.test(id)) {
    alert("아이디 형식이 올바르지 않습니다.");
    return;
  }
//AJAX 방식 자바스크립트로, 페이지 전체를 새로고침하지 않고, 서버와 비동기 통신을 하는 기술
//get 방식, 결과만 받으면 됨
//브라우저로 문자열 하나만 반환
  fetch("ProjController?cmd=idCheck&pId=" + encodeURIComponent(id))
    .then(res => res.text())
    .then(result => {
      if(result === "ok") { //브라우저로 문자열 하나만 반환
        alert("사용 가능한 아이디입니다.");
        idCk = true;
      } else {
        alert("이미 사용 중인 아이디입니다.");
        idCk = false;
        pId.focus();
      }
    });
});

// 아이디 값이 바뀌는 순간 중복확인 무효화
pId.addEventListener("input", () => {
  idCk = false;
});
//회원가입 함수
function join() {
	
  cmd.value = "join";//탭 변경시 cmd값 주고 있지만 버그 생길 가능성이 있으므로 확실하게 준다.
	
  const id = pId.value.trim();
  const pw = pPw.value.trim();
  const pwRe = pPwRe.value.trim();

  // 빈 값
  if(id === "" || pw === "" || pwRe === "") {
    alert("모든 항목을 입력하세요.");
    return;
  }

  // 비밀번호 일치
  if(pw !== pwRe) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

  // 정규식
  const idReg = /^[a-zA-Z0-9]{4,12}$/;
  const pwReg = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;

  if(!idReg.test(id) || !pwReg.test(pw)) {
    alert("입력 형식을 확인하세요.");
    return;
  }

  if(!idCk) {
    alert("아이디 중복확인을 해주세요.");
    return;
  }
  // 서버 전송
  document.getElementById("f").submit();
}










