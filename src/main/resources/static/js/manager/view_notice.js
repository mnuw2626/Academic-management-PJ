function confirmDelete(noticeNo) {
    if (confirm('삭제하시겠습니까?')) {
        // CSRF 토큰을 가져오기 위한 메타 태그 검색
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch('/manager/notice_delete/' + noticeNo, {
            method: 'POST',  // POST 요청
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeader]: csrfToken
            },
            credentials: 'same-origin'
        })
            .then(response => {
                if (response.ok) {
                    // 요청이 성공하면 페이지를 리디렉션
                    window.location.href = '/manager/manager_notice';
                } else {
                    alert('삭제 요청에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('삭제 요청 오류:', error);
                alert('삭제 요청에 실패했습니다.');
            });
    }
}

