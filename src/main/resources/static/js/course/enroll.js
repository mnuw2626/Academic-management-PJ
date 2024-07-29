const viewBtn = document.getElementById('course-find-btn');
const couresTableBobyTag = document.getElementById('course-table-tbody');

viewBtn.onclick = () => {
    fetch(`lectures`)
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