const startDateInput = document.getElementById('start-date');
const endDateInput = document.getElementById('end-date');

// 수강 신청 날짜를 오늘 날짜보다 이후로
const nowDate = new Date();
const year = nowDate.getFullYear();
const month = (nowDate.getMonth()+1).toString().padStart(2, '0');
const date = nowDate.getDate().toString().padStart(2, '0');
startDateInput.value = `${year}-${month}-${date}`;
endDateInput.value = `${year}-${month}-${date}`;
startDateInput.setAttribute('min', `${year}-${month}-${date}`);
endDateInput.setAttribute('min', `${year}-${month}-${date}`);

// 시작날짜 변경 시 종료 날짜도 시작 날짜보다 더 뒤의 날짜로
startDateInput.onchange = () => {
    const startDate = startDateInput.value;
    endDateInput.setAttribute('min', startDate);
}

/**********************************************************************/