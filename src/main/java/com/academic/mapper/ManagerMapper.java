package com.academic.mapper;

import com.academic.dto.StdDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerMapper {
    void insert_std(StdDTO stdDTO);

}
