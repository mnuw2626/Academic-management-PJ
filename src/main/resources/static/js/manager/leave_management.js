function status_function(button) {
    const form = button.parentElement;
    const stdNo = form.querySelector('input[name="stdNo"]').value;
    let status = form.querySelector('input[name="status"]').value;

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    let action = '';
    if (status === '처리중') {
        action = "leave";
    } else if (status === '복학 처리중') {
        action = "return";
    }

    console.log(action); // 확인용 콘솔 출력


    fetch(`/manager/${action}/update`, {  // 동적으로 경로 설정
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            [csrfHeader]: csrfToken
        },
        body: new URLSearchParams({ 'stdNo': stdNo })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                if (action === 'leave') {
                    status = '휴학';
                } else {
                    status = '복학';
                }
            } else {
                console.error('Update failed');
            }
        })
        .catch(error => console.error('Error:', error));
}