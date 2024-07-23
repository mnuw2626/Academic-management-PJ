package com.academic.service;

import com.academic.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeManagerService {
    @Autowired
    ManagerMapper managerMapper;

}
