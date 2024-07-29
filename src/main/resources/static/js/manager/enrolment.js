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