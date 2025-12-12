/* ------------ form 관련 요소들 -----------------*/
const f = document.forms[0];

/* ------------ 함수 -----------------*/
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', ()=>{
		let type = btn.id;
		if(type === 'loginBtn'){
			login();
		}else if(type === 'mainBtn'){
			location.href = 'MemberController?cmd=mainPage';
		}
		
	});
});
// 로그인
function login(){
	// 1. 아이디 및 비밀번호 빈 값 검증
	if(f.mId.value == '' || f.mPw.value == ''){
		alert("회원 정보를 입력하세요.");
		return;
	}
	
	// 2. mId, mPw, cmd 데이터 json으로 변환 후 전송
	let formData = new FormData(f);
	let jsonData = JSON.stringify(
				Object.fromEntries(formData.entries())
				);
	fetch('MemberAsyncController', {
			method : 'POST',
			body : jsonData,
			headers : {
				'Content-type' : 'application/json; charset=UTF-8'
			}
		})
		.then(response => response.json())
		.then(data => {
			if(data.result === 'success'){
				location.href = 'MemberController?cmd=mainPage';
			}else{
				alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
			}
		})
		.catch(err => console.log(err));
}












