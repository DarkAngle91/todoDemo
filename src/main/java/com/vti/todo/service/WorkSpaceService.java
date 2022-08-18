package com.vti.todo.service;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.AccountEntity;
import com.vti.todo.entity.WorkSpaceEntity;
import com.vti.todo.repository.AccountRepository;
import com.vti.todo.repository.WorkSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSpaceService {

    @Autowired
    private WorkSpaceRepository workSpaceRepository;

    @Autowired
    private AccountRepository accountRepository;

    public WorkSpaceResponse createWorkSpace(WorkSpaceRequest request) {
        WorkSpaceEntity workSpace = new WorkSpaceEntity();
        workSpace.setName(request.getWorkSpaceName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity account = accountRepository.findByEmail(authentication.getName());

        // lấy account hiện tại
        workSpace.setAccount(account);
        workSpaceRepository.save(workSpace);
        return new WorkSpaceResponse(workSpace.getId(),workSpace.getName());
    }

//    public List<WorkSpaceResponse> getCurrentWorkSpace(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        List<WorkSpaceResponse> response = workSpaceRepository.getWorkSpaceResponseByUserEmail(email);
//
//    }

    //Sql
//    SELECT NEW com.vti.todo.dto.response.WorkSpaceResponse(w.id, w.name, count(t))
//    FROM WorkSpaceEntity w left join TaskEntity t ON w = t.workSpace
//    where w.account.email = :email
//    GROUP BY w


}
