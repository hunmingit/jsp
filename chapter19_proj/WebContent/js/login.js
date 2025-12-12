const loginTab = document.getElementById("loginTab");
const joinTab = document.getElementById("joinTab");
const pId = document.getElementById("pId");
const pPw = document.getElementById("pPw");
const cmd = document.getElementById("cmd");
const pPwRe = document.getElementById("pPwRe");

let idCk=pwCk=pwReCk=false; // 검증

// 기본: 로그인 탭
let isLogin = true;

// 로그인 탭 클릭
loginTab.onclick = () => { 
	if(!isLogin){
		pId.value = "";
		pPw.value = "";
		pPwRe.value = "";		
	}
  isLogin = true;

  loginTab.classList.add("active");
  joinTab.classList.remove("active");

  pPwRe.style.display = "none";   // 비밀번호 확인 숨기기
  cmd.value = "login";
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
  isLogin = false;

  joinTab.classList.add("active");
  loginTab.classList.remove("active");

  pPwRe.style.display = "block";  // 비밀번호 확인 보이기
  cmd.value = "join";
  loginSubBtn.textContent = "회원가입";
  adminInfo.style.display = "none"; //어드민 정보 숨기기
};

document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', ()=>{
		switch(btn.textContent){
			case "로그인" :
				login();
					break;
			case "회원가입" :
				join();
					break;
		}	
	});
});

function login(){
	

}


function join() {
  const id = pId.value.trim();
  const pw = pPw.value.trim();
  const pwRe = pPwRe.value.trim();

  // 1. 빈 값 체크
  if(id === "") {
    alert("아이디를 입력하세요.");
    pId.focus();
    return;
  }
  if(pw === "") {
    alert("비밀번호를 입력하세요.");
    pPw.focus();
    return;
  }
  if(pwRe === "") {
    alert("비밀번호 확인을 입력하세요.");
    pPwRe.focus();
    return;
  }

  // 2. 비밀번호 동일 체크
  if(pw !== pwRe) {
    alert("비밀번호가 일치하지 않습니다.");
    pPwRe.focus();
    return;
  }

  // 3. 정규식 체크 
  const idReg = /^[a-zA-Z0-9]{4,12}$/;
  const pwReg = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;

  if(!idReg.test(id)){
    alert("아이디는 영문 또는 숫자 4~12자입니다.");
    return;
  }

  if(!pwReg.test(pw)) {
    alert("비밀번호는 6~16자, 영문/숫자/특수문자 허용");
    return;
  }

  // 4. 서버 전송
  console.log("회원가입 진행:", id, pw);

  document.getElementById("f").submit();
}











