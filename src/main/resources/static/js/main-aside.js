const courseEnrollATag = document.getElementById('course-enroll-a');

courseEnrollATag.onclick = () => {
    fetch(`/course/is/enroll`)
        .then(resp => resp.json())
        .then(value => {
            console.log('v: ', value);
            if (value){
                // course/enroll로 이동
                window.location.href = '/course/enroll';
            }
            else {
                alert('수강 신청 기간이 아닙니다.');
            }
        })
}