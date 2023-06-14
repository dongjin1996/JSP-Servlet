function chkForm() {
	var f = document.frm;
	
	if(frm.title.value == '') {
		alert("제목을 입력하세요.")
		return false; 
	}
	
	if(frm.user_id.value == '') {
		alert("아이디를 입력해주세요.");
		return false;
	}
	
	if(frm.content.value == '') {
		alert("글내용을 입력해주세요.");
		return false;
	}
	f.submit();
}

function chkDelete(news_no) {
	const result = confirm("삭제하시겠습니까?");
	
	if(result) {
		const url = location.origin;
		location.href = url + "/news/delete?news_no=" + news_no;
	}else {
		false;
	}
}