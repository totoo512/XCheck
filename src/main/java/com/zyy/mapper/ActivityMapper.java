package com.zyy.mapper;

import com.zyy.annotation.AutoFill;
import com.zyy.dto.ActivityListDTO;
import com.zyy.entity.Activity;
import com.zyy.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper {
    /**
     * 新增活动
     * @param activity
     */
    @AutoFill(OperationType.INSERT)
    void insert(Activity activity);

    /**
     * 根据id查询活动
     * @param id
     * @return
     */
    @Select("select * from activity where id = #{id}")
    Activity selectById(Integer id);

    /**
     * 活动列表按条件查询
     * @param activityListDTO
     * @return
     */
    List<Activity> listQuery(ActivityListDTO activityListDTO);
}
