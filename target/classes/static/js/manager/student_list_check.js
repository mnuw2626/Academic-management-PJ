const checkBtn = document.querySelector('.check_btn');
const stdListTable = document.querySelector('.std_list_table');

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
    stdListTable.innerHTML = '<tr>\n' +
        '                    <th width="100px">학번</th>\n' +
        '                    <th width="70px">이름</th>\n' +
        '                    <th width="130px">단과대학</th>\n' +
        '                    <th width="100px">학과</th>\n' +
        '                    <th width="140px">학년</th>\n' +
        '                    <th width="50px">학기</th>\n' +
        '                </tr>';
    for(stdObj of stdList){
        stdListTable.insertAdjacentHTML(`beforeend`,
                `
                    <tr>
                        <td width="100px">${stdObj.stdNo}</td>
                        <td width="70px">${stdObj.name}</td>
                        <td width="130px">${stdObj.collegeId}</td>
                        <td width="100px">${stdObj.deptId}</td>
                        <td width="140px">${stdObj.grade}</td>
                        <td width="50px">${stdObj.semester}</td>
                    </tr>`
        );
    }
}