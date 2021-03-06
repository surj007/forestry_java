package com.forestry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.forestry.bean.Test;
import com.forestry.dao.TestDao;

@Service
public class TestService {
    @Autowired
    private TestDao testDao;

    public List<Test> getData() {
        return testDao.getData();
    }
}
