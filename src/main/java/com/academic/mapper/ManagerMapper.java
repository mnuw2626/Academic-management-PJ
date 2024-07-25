package com.academic.mapper;

import com.academic.dto.StdDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    void insert_std(StdDTO stdDTO);
    List<StdDTO> select_std(StdDTO stdDTO);
}
