function status_function(button) {
    const form = button.parentElement;
    const stdNo = form.querySelector('input[name="stdNo"]').value;
    let status = form.querySelector('input[name="status"]').value;

    // leaveCount 요소를 가져와서 값을 1 증가시킵니다.
    let countElement = form.querySelector('input[name="leaveCount"]');
    let updatedCount = parseInt(countElement.value) + 1;

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    let action = '';
    if (status === '처리중') {
        action = "leave";
    } else if (status === '복학 처리중') {
        action = "return";
    }

    fetch(`/manager/${action}/update`, {  // 동적으로 경로 설정
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            [csrfHeader]: csrfToken
        },
        body: new URLSearchParams({
            'stdNo': stdNo,
            'leaveCount': updatedCount.toString()  // 증가된 leaveCount 값을 서버로 전송
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                if (action === 'leave') {
                    status = '휴학';
                    countElement.value = updatedCount.toString();
                    button.innerText = '휴학';
                } else {
                    status = '복학';
                    button.innerText = '복학';
                }
            } else {
                console.error('Update failed');
            }
        })
        .catch(error => console.error('Error:', error));
}