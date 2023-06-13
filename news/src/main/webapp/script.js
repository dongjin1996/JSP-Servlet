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