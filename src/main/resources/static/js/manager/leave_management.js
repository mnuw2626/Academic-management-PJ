function status_function(button){
    const form = button.parentElement;
    const stdNo = form.querySelector('input[name="stdNo"]').value;
    const action = button.textContent.trim() === '처리중' ? 'leave' : 'return';


    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

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
                    button.textContent = '휴학';
                } else {
                    button.textContent = '복학';
                    form.closest('tr').remove(); // 복학 신청 시 해당 행 제거
                }
            } else {
                console.error('Update failed');
            }
        })
        .catch(error => console.error('Error:', error));
}

