// DOMContentLoaded 이벤트: document.addEventListener('DOMContentLoaded', ...)를 사용하여 HTML이 완전히 로드된 후에 자바스크립트 코드가 실행되도록 보장
// input 이벤트리스너를 처리하기 위한 안전 장치임
document.addEventListener('DOMContentLoaded', () => {
    const viewBtn = document.getElementById('course-find-btn');
    const couresTableBobyTag = document.getElementById('course-table-tbody');
    const codeTag = document.getElementById('course-code-tag');
    const nameTag = document.getElementById('course-name-tag');

    viewBtn.onclick = () => {
        const code = codeTag.value.trim();
        console.log(code);
        const url = code ? `/course/lectures?code=${code}` : '/course/lectures'; // 조건에 따라 URL 설정
        console.log(url);
        fetch(url)
            .then(response => {
                    if (!response.ok) { // 응답 상태가 200번대가 아닐 경우 오류 처리
                        throw new Error('네트워크 오류');
                    }
                    return response.json();
            })
            .then(value => {
                console.log(value);
                couresTableBobyTag.innerHTML = '';

                for (const lecture of value){
                    couresTableBobyTag.insertAdjacentHTML(`beforeend`,
                        `<tr>
                                 <td>${lecture.no}</td>
                                <td>${lecture.type}</td>
                                <td>${lecture.grade}</td>
                                <td>${lecture.credit}</td>
                                <td><button class="class-btn">수강</button></td>
                                <td>${lecture.code}</td>
                                <td>${lecture.name}</td>
                                <td>${lecture.professor}</td>
                                <td>${lecture.starTime} ~ ${lecture.endTime} </td>
                                <td>${lecture.week}</td>
                                <td>${lecture.room}</td>
                                <td>${lecture.capacity} / ${lecture.capacity} </td>
                                <td><span class="class-sign-re">재수강</span></td>
                              </tr>`
                    );
                }
            });
    }

    codeTag.addEventListener('input', (e) => {
        const query = e.target.value ? e.target.value.trim() : '';
        if (query) {
            fetch(`/course/lectures?code=${query}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('네트워크 오류');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('서버 응답 데이터 : ',data);
                    // 배열의 첫 번째 요소를 가져와 name 속성에 접근(JSON 형태의 배열로 가져옴)
                    const lectureData = data[0] || {}; // 데이터가 비어있을 경우를 대비하여 빈 객체로 초기화
                    // 속성값이 있는 지 확인하여 알수없는 경우는 '교과목명'이 뜨도록
                    nameTag.placeholder = lectureData.name ? lectureData.name : '교과목명';
                })
                .catch(error => console.error('Error : ', error));
        }else {
            nameTag.placeholder = '교과목명'; // 입력이 비어있을 경우 기본 placeholder 설정
        }
    })
})