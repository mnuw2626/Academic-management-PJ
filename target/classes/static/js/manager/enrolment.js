const startDateInput = document.getElementById('start-date');
const endDateInput = document.getElementById('end-date');
const formTag = document.getElementsByClassName('enrolment-form');
// 설정 버튼
const enrolmentBtn = document.getElementById('enrolment-btn');

enrolmentBtn.onclick = () =>{
    const startDate = startDateInput.value;
    const endDate = endDateInput.value;
    
    fetch(`/check/enrolment?startDate=${startDate}&endDate=${endDate}`)
        .then(response => response.json())
        .then(date => {
            console.log(date)

           //결과 화면 구현
            if(date){
                formTag.innerHTML = '';

                formTag.insertAdjacentHTML(`beforeend`, `
                     <label class="enrolment-input-data">
                        <input type="date" id="start-date" name="startDate" required>
                        ~
                        <input type="date" id="end-date" name="endDate" required>
                    </label>
                    <button class="enrolment-btn" id="enrolment-btn">설정</button>
                `);
            }
            else {
                formTag.innerHTML = '';
                formTag.insertAdjacentHTML(`beforeend`, `
                     <h2>수강신청기간입니다.</h2>
                     <button class="enrolment-btn">종료</button>
                `);
            }
        })
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