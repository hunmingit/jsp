const loginTab = document.getElementById("loginTab");
const joinTab = document.getElementById("joinTab");
const pId = document.getElementById("pId");
const pPw = document.getElementById("pPw");
const cmd = document.getElementById("cmd");
const pPwRe = document.getElementById("pPwRe");
const idCkBtn = document.getElementById("idCkBtn");

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

// 아이디 중복확인 버튼
idCkBtn.addEventListener("click", () => {
  const id = pId.value.trim();

  if(id === "") {
    alert("아이디를 입력하세요.");
    pId.focus();
    return;
  }

  const idReg = /^[a-zA-Z0-9]{4,12}$/;
  if(!idReg.test(id)) {
    alert("아이디 형식이 올바르지 않습니다.");
    return;
  }

  fetch("ProjController?cmd=idCheck&pId=" + encodeURIComponent(id))
    .then(res => res.text())
    .then(result => {
      if(result === "ok") {
        alert("사용 가능한 아이디입니다.");
        idCk = true;
      } else {
        alert("이미 사용 중인 아이디입니다.");
        idCk = false;
        pId.focus();
      }
    });
});

// 아이디 변경 시 중복확인 무효화
pId.addEventListener("input", () => {
  idCk = false;
});

function join() {
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

  // 🔥 중복확인 최종 체크
  if(!idCk) {
    alert("아이디 중복확인을 해주세요.");
    return;
  }

  // 서버 전송
  document.getElementById("f").submit();
}










