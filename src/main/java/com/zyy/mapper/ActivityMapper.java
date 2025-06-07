package com.zyy.mapper;

import com.zyy.annotation.AutoFill;
import com.zyy.entity.Activity;
import com.zyy.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper {
    /**
     * 新增活动
     * @param activity
     */
    @AutoFill(OperationType.INSERT)
    void insert(Activity activity);
}
