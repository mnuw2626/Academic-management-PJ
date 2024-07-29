const startDateInput = document.getElementById('start-date');
const endDateInput = document.getElementById('end-date');
const formTag = document.getElementsByClassName('enrolment-form');
// 설정 버튼
const enrolmentBtn = document.getElementById('enrolment-btn');

enrolmentBtn.onclick = () =>{
    const startDate = startDateInput.value;
    const endDate = endDateInput.value;

    console.log(formTag)

    //토큰 가져옴
    const csrfToken = document.querySelector("meta[name=_csrf]").getAttribute('content');

    // POST request 생성
    fetch(`/check/enrolment`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            "X-CSRF-TOKEN": csrfToken
        },
        body: new URLSearchParams({
            'startDate': startDate,
            'endDate': endDate
        })
    })
    .then(response => response.json())
    .then(date => {
        console.log(date)
       //결과 화면 구현
        if(date) {
            enrolmentBtn.textContent = '종료';
            return false;
        }
        else {
                enrolmentBtn.textContent = '설정';
        }
    }).catch(error => console.error('Error:', error));
}

/*
// 수강 신청 날짜를 오늘 날짜보다 이후로
const nowDate = new Date();
const year = nowDate.getFullYear();
const month = (nowDate.getMonth()+1).toString().padStart(2, '0');
const date = nowDate.getDate();
startDateInput.value = `${year}-${month}-${date}`;
endDateInput.value = `${year}-${month}-${date}`;
startDateInput.setAttribute('min', `${year}-${month}-${date}`);
endDateInput.setAttribute('min', `${year}-${month}-${date}`);

// 시작날짜 변경 시 종료 날짜도 시작 날짜보다 더 뒤의 날짜로
startDateInput.onchange = () => {
    const startDate = startDateInput.value;
    endDateInput.setAttribute('min', startDate);
}

*/
/**********************************************************************/