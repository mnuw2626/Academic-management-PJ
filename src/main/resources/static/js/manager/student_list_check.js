const checkBtn = document.querySelector('.check_btn');
const stdListTable = document.querySelector('.std_list_table');

checkBtn.onclick = show_std_function;

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

/******************* 단과대학 및 학과 DB에서 조회 ********************/
// 단과대학, 학과 select Tag 가져옴
const collegeSelectTag = document.getElementById('collegeId');
const departSelectTag = document.getElementById('deptId');

collegeSelectTag.onchange = () => {
    const collegeId = collegeSelectTag.value; //단과대학의 id값 가져옴
    fetch(`/college/${collegeId}/depart`)
        .then(resp => resp.json())
        .then(departList => {
            departSelectTag.innerHTML = ''; //초기화
            for (const depart of departList) {
                console.log(depart)
                departSelectTag.insertAdjacentHTML(`beforeend`, `
                    <option value="${depart.id}">
                            ${depart.name}
                    </option>`);
            }
        })
}
/*******************************************************************/