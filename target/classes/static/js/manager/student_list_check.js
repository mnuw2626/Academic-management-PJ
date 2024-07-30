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