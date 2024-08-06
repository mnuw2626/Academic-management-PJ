/*
const viewBtn = document.getElementById('course-find-btn');
const couresTableBobyTag = document.getElementById('course-table-tbody');

console.log(viewBtn);

//조회버튼 클릭 시 전체 조회
viewBtn.onclick = () => {
    fetch(`/course/lectures`)
        .then(response => response.json())
        .then(value => {
            couresTableBobyTag.innerHTML = '';
            for (const lecture of value){
                couresTableBobyTag.insertAdjacentHTML(`beforeend`,
                    `<tr>
                            <td>${lecture.no}</td>
                            <td>${lecture.type}</td>
                            <td>${lecture.grade}</td>
                            <td>${lecture.credit}</td>
                            <td>${lecture.code}</td>
                            <td>${lecture.name}</td>
                            <td>${lecture.professor}</td>
                            <td>${lecture.starTime} ~ ${lecture.endTime} (${lecture.week})</td>
                            <td>${lecture.room}</td>
                            <td>${lecture.capacity} / ${lecture.capacity} </td>
                            <td><span class="class-sign-re">재수강</span></td>
                            <td>
                                <button class="lecture-find-btn">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                    <span>조회</span>
                                </button>
                            </td>
                          </tr>`
                );
            }
        });
}
 */

const modalView = document.getElementById('user-info-modal');
const  closeButton = document.getElementById('modal-close-btn');
const classSign = document.getElementsByClassName('class-sign');


//강의계획서 조회버튼 클릭 시
function modal_report_view(code){
    // 모달창 활성화
    modalView.style.display = 'block';

    //버튼 클릭 후 패치로 code 응용하여 강의의 강의계획서 불어와야함
    fetch(`/course/lectures?code=${code}`)
        .then( response => {
            if (!response.ok) {
                throw new Error('네트워크 오류');
            }
            return response.json();
        })
        .then(data => {
            const lectureData = data[0] || {}; // 데이터가 비어있을 경우를 대비하여 빈 객체로 초기화
            console.log(lectureData.report);
            modalView.querySelector('span').textContent = lectureData.report ? lectureData.report : '';
        })
        .catch(error => {
            console.error('Error:', error);
            modalContent.querySelector('span').textContent = '강의 계획서를 불러오는 데 오류가 발생했습니다.';
        });
}

// 모달창의 닫기 버튼 클릭 시 모달창 비활성화
closeButton.onclick = () => {
    modalView.style.display = 'none';
}

