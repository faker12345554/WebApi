package com.admin.admin.controller.dw_task;

import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.service.dw_task.Tasking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private Tasking tasking;

    @GetMapping("/getperson")
    public List<Personinformation> getperson(){
        return tasking.GetPerson();
    }
}
