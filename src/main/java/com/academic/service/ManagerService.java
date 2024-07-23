package com.academic.service;

import com.academic.dto.StdDTO;
import com.academic.mapper.ManagerMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    public void manager_add_std(StdDTO stdDTO) {
        managerMapper.insert_std(stdDTO);
    }
}
