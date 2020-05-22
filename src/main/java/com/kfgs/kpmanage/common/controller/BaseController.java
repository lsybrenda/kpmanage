package com.kfgs.kpmanage.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

        protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
            Map<String, Object> rspData = new HashMap<>();
            rspData.put("items", pageInfo.getRecords());
            rspData.put("total", pageInfo.getTotal());
            return rspData;
        }
}
