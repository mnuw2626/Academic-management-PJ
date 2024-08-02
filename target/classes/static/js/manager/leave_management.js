function status_function(button){
    const form = button.parentElement;
    fetch('/leave/check', {  // 고정된 경로 사용
        method: 'POST',
        body: new FormData(form)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            button.textContent = '휴학'; // 버튼 텍스트를 '휴학'으로 변경
        } else {
            console.error('Update failed');
        }
    })
}

