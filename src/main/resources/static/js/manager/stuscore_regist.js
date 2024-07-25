// 학과 select Tag 가져옴
const collegeSelectTag = document.getElementById('collegeId');
const departSelectTag = document.getElementById('deptId');
collegeSelectTag.onchange = () => {
    const collegeId = collegeSelectTag.value;
    fetch(`/college/${collegeId}/depart`)
        .then(resp => resp.json())
        .then(value => {

        })
}