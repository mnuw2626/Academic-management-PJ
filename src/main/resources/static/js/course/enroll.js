// CSRF 토큰을 meta 태그에서 가져오기
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

/*
// DOMContentLoaded 이벤트: document.addEventListener('DOMContentLoaded', ...)를 사용하여 HTML이 완전히 로드된 후에 자바스크립트 코드가 실행되도록 보장
// input 이벤트리스너를 처리하기 위한 안전 장치임
document.addEventListener('DOMContentLoaded', () => {

    function fetchCourseName() {
        const codeTag = document.getElementById('course-code-tag');
        const nameTag = document.getElementById('course-name-tag');
        const code = codeTag.value.trim();
        // 교과목코드 입력 시 해당하는 교과목명을 보이도록
        if (code) {
            fetch(`/course/enroll?code=${code}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('네트워크 오류');
                    }
                    return response.json();
                })
                .then(lectures => {
                    console.log('서버 응답 데이터 : ', lectures);
                    // 배열의 첫 번째 요소를 가져와 name 속성에 접근(JSON 형태의 배열로 가져옴)
                    //const lectureData = lectures[0] || {}; // 데이터가 비어있을 경우를 대비하여 빈 객체로 초기화
                    // 속성값이 있는 지 확인하여 알수없는 경우는 '교과목명'이 뜨도록
                    nameTag.placeholder = lectureData.name ? lectureData.name : '교과목명';
                })
                .catch(error => console.error('Error : ', error));
        }else {
            nameTag.placeholder = '교과목명'; // 입력이 비어있을 경우 기본 placeholder 설정
        }
    }

});
*/

const modalView = document.getElementById('user-info-modal');
const  closeButton = document.getElementById('modal-close-btn');

// 수강 신청 버튼을 누를 시
function class_registration(code, btn){
    // 모달창 활성화
    modalView.style.display = 'block';

    fetch(`/enroll/regist/${code}`, {
        method: "POST",
        headers:
            {
                'Content-Type': 'application/json',
                'X-Csrf-Token': csrfToken
            }
    })
        .then(response => {
            return response.text().then(text => {
                // 응답이 성공적이고 상태 코드가 200일 경우
                if (response.ok) {
                    console.log("수강 신청 성공");
                    btn.disabled = true; // 클릭한 버튼 비활성화
                    btn.textContent = "수강됨";  // 버튼 텍스트 변경
                    showModalMessage(text); // 성공 메시지를 모달에 표시
                } else {
                    console.log("수강 시간 중복 또는 등록 실패");
                    showModalMessage(text); // 실패 메시지를 모달에 표시
                }
            });
        }).catch(error => {
        console.error("Error:", error);
        showModalMessage("오류가 발생했습니다."); // 오류 메시지를 모달에 표시
    });
}

// 모달창의 메시지를 업데이트하는 함수
function showModalMessage(message) {
    const modalMessageElement = document.getElementById('modal-message'); // 모달 내 메시지 요소
    modalMessageElement.textContent = message; // 메시지 업데이트
}

// 모달창의 확인 버튼 클릭 시 모달창 비활성화
closeButton.onclick = () => {
    location.reload();
}

// 과목코드에 따라 과목명 보여줌
function updateCourseName() {
    const courseCode = document.getElementById("course-code-tag").value;

    if (courseCode) {
        fetch(`/course/getLectureName?code=${courseCode}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                document.getElementById("course-name-tag").value = data; // 교과목명 입력란 업데이트
            })
            .catch(error => {
                console.error('Error fetching lecture name:', error);
                document.getElementById("course-name-tag").value = ""; // 오류 발생 시 교과목명 초기화
            });
    } else {
        document.getElementById("course-name-tag").value = ""; // 코드 입력란이 비어있을 경우 교과목명 초기화
    }
}

