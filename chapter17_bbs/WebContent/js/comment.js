function insert_comment(f){
    if(!f.writer.value){
        alert("작성자를 입력하세요");
        return;
    }
    if(!f.content.value){
        alert("내용을 입력하세요");
        return;
    } 
    if(!f.pw.value){
        alert("비밀번호를 입력하세요");
        return;
    }

    let formData = new FormData(f); //form태그에 있는 값들을 formData객체에 저장

    //직렬화 두 개 중 택 일 
    let serializedData = new URLSearchParams(formData).toString(); //쿼리스트링 형태로 직렬화

    //let jsonData = JSON.stringify(Object.fromEntries(formData.entries())); //json 형태로 직렬화

	fetch('CommentController?' + serializedData)
		.then(response => response.json())
		.then(data => {
			console.log(data);
			showCommList();
		})
		.catch(err => console.log(err));
}

function showCommList(){
	//cmd // b_idx //javsscript, session 영역
	let b_idx = new URLSearchParams(location.search).get("b_idx"); //url에서 특정 파라미터 뽑아오기
	
	let sendData = `cmd=commList&b_idx=${b_idx}`;
	let msg = ``;
	fetch('CommentController?' + sendData)
		.then(response => response.json()) //자바에서 자바스크립트로 바로 보내서 쓸 수 없다 형태를 바꿔줘야함 
		.then(data => {
            let cList  = JSON.parse(data.cList); //문자열로 된 json형태를 자바스크립트 객체로 변환
            cList.forEach(cvo => {
	            msg += `<tr>`;
	            msg += `<td>${cvo.c_idx}</td>`;
	            msg += `<td>${cvo.writer}</td>`;
	            msg += `<td>${cvo.content}</td>`;
	            msg += `<td>${myTime(cvo.reg_date)}</td>`;
	            msg += `<td><button type="button" onclick="removeComm(${cvo.c_idx})">삭제</button></td>`;
	            msg += `</tr>`;
	
        	});
            if(msg == ''){
                msg += `<tr>`;
                msg += `<td colspan="5">등록된 댓글이 없습니다.</td>`;           
                msg += `</tr>`;
            }
            document.querySelector("#commBody").innerHTML = msg;
		})
        .catch(err => console.log(err));
}
showCommList();
//날짜가 이상하게 나옴
// https://www.unixtimestamp.com/ 
// unixTimeStamp to date
function myTime(unixTimeStamp){
    //1. 밀리초로 넘어오면 1000으로 나누기
    let myDate = new Date(unixTimeStamp);

    let date = myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate();
    return date;
}
//댓글 삭제
function removeComm(c_idx){
		
    if(confirm("정말 삭제하시겠습니까?")){
        alert("댓글이 삭제되었습니다.");
        let sendData = `cmd=removeComment&c_idx=${c_idx}`;
        fetch('CommentController?' + sendData)
		.then(response => response.json())
		.then(data => {
			console.log(data);
			showCommList();
		})
		.catch(err => console.log(err));
     
    }
}






