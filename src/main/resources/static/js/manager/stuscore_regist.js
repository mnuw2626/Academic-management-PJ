// 단과대학, 학과 select Tag 가져옴
const collegeSelectTag = document.getElementById('collegeId');
const departSelectTag = document.getElementById('deptId');

collegeSelectTag.onchange = () => {
    const collegeId = collegeSelectTag.value;
    fetch(`/college/${collegeId}/depart`)
        .then(resp => resp.json())
        .then(departList => {
            departSelectTag.innerHTML = '';
            for (const depart of departList) {
                console.log(depart)
                departSelectTag.insertAdjacentHTML(`beforeend`, `
                    <option value="${depart.id}">
                            ${depart.name}
                    </option>`);
            }
        })
}