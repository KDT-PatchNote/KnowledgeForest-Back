/* 루트 경로 담은 함수 */
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));

	return contextPath;
}

// 삭제 버튼 클릭시
function clickDeleteBtn(boardNum) {
	// 확인, 취소 버튼 선택
	const isdelete = confirm('정말 삭제하시겠습니까?');

	// 확인 버튼 클릭시
	if (isdelete) {
		$.ajax({
			/* 자유게시판 글 삭제 컨트롤러로 이동, 글 넘버 쿼리스트링으로 전달 */
			url: getContextPath() + "/admin/admin-boardDeleteOk.ad?boardNum=" + boardNum,
			type: "GET",
			/* 자유게시판 글 삭제 성공 시 알람창 뜨면서 목록 페이지로 이동 */
			success: () => {
				alert('삭제가 완료되었습니다.');
				location.href = getContextPath() + "/admin/admin-boardlist.ad";
			},
			/* 삭제 불가시 알람창 */
			error: (xhr, status, error) => {
				console.error("자유게시판 글 삭제 실패:", error);
				alert("삭제 실패했습니다.");
			}
		});
	}
}