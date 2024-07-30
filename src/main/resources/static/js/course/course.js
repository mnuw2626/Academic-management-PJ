const viewBtn = document.getElementById('course-find-btn');
const couresTableBobyTag = document.getElementById('course-table-tbody');

console.log(viewBtn);

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