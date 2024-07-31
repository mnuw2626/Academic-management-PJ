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

const viewReportBtns = document.getElementsByClassName('lecture-find-btn');
const messageDiv = document.getElementById('output-message');
console.log(viewReportBtns);

viewReportBtns.forEach(function (button) {
    button.addEventListener('click', function () {
        var messageSection = document.getElementById('output-message');
        var message = button.getAttribute('data-message');

        messageSection.textContent = message;
        messageSection.style.display = 'block';
    });
});
