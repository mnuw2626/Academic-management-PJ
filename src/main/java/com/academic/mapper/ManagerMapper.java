package com.academic.mapper;


import com.academic.dto.*;
import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.NoticeDTO;
import com.academic.dto.StdDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ManagerMapper {
    void insert_std(StdDTO stdDTO);

    List<StdDTO> select_all_std();

    List<StdDTO> select_std(
            //param => 여러개니까 써줘야함
            @Param("collegeId") Integer collegeId,
            @Param("deptId") Integer deptId,
            @Param("grade") Integer grade,
            @Param("semester") Integer semester,
            @Param("name") String name,
            @Param("stdNo") String stdNo
    );

    List<StdDTO> select_all_std_tuition();

    List<CollegeDTO> select_colleges();

    List<DepartmentDTO> select_dept(Integer collegeId);

    List<TuitionDTO> select_std_scholarship();

    void insert_scholarship(List<TuitionDTO> tuitionDTOS);

    /***************휴/복학****************/

    List<LeaveDTO> select_all_std_leaves();

    List<LeaveDTO> select_all_std_returns();

    int update_leave_std_status(@Param("stdNo") Integer stdNo);

    int update_return_std_status(@Param("stdNo") Integer stdNo);

    void delete_leave_application(@Param("stdNo") Integer stdNo);


    /***************공지사항****************/

    List<NoticeDTO> select_notices();

    NoticeDTO select_notice(String noticeNo);

    Integer count();  // 게시글 수를 반환하는 메서드

    void insert_notice(NoticeDTO noticeDTO);

}
