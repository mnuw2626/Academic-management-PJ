/* 수강 신청 날짜 가져오기 */
const startDateInput = document.getElementById('start-date');
const endDateInput = document.getElementById('end-date');

// 설정 버튼
const enrolmentBtn = document.getElementById('enrolment-btn');

enrolmentBtn.onclick = () =>{

}


// 수강 신청 날짜를 오늘 날짜보다 이후로
const nowDate = new Date();
const year = nowDate.getFullYear();
const month = (nowDate.getMonth()+1).toString().padStart(2, '0');
const date = nowDate.getDate();
startDateInput.value = `${year}-${month}-${date}`;
endDateInput.value = `${year}-${month}-${date}`;
startDateInput.setAttribute('min', `${year}-${month}-${date}`);
endDateInput.setAttribute('min', `${year}-${month}-${date}`);
/**********************************************************************/