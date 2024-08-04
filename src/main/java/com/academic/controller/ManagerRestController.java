package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.CourseScoreService;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.academic.service.CourseScoreService.*;

@RestController
public class ManagerRestController {
    @Autowired
    ManagerService managerService;

    @Autowired
    CourseScoreService courseScoreService;


}
