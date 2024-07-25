const checkBtn = document.querySelector('.check_btn');
const stdListDiv = document.querySelector('.std_list_div');

checkBtn.onclick = show_std_function

function show_std_function() {
    fetch(`/manager/student_list`)
        .then(response => {
            if(response.ok){
                return response.json();
            }
        })
        .then(create_std_list)
}

function create_std_list(stdList) {
    console.log(stdList);
    stdListDiv.innerHTML = '';
    for(stdObj of stdList){
        stdListDiv.insertAdjacentHTML(`beforeend`,
            `<table>
                    <tr>
                        <th width="100px">학번</th>
                        <th width="70px">이름</th>
                        <th width="130px">단과대학</th>
                        <th width="100px">학과</th>
                        <th width="140px">학년</th>
                        <th width="50px">학기</th>
                    </tr>
                    <tr>
                        <td width="100px">${stdObj.std_no}</td>
                        <td width="70px">${stdObj.name}</td>
                        <td width="130px">${stdObj.college_id}</td>
                        <td width="100px">${stdObj.dept_id}</td>
                        <td width="140px">${stdObj.grade}</td>
                        <td width="50px">${stdObj.semester}</td>
                    </tr>
                </table>`
        );
    }
}