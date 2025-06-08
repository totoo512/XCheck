package com.zyy.mapper;

import com.zyy.annotation.AutoFill;
import com.zyy.dto.ActivityListDTO;
import com.zyy.entity.Activity;
import com.zyy.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
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
    Activity selectById(Integer id);

    /**
     * 活动列表按条件查询
     * @param activityListDTO
     * @return
     */
    List<Activity> listQuery(ActivityListDTO activityListDTO);

    /**
     * 根据id删除活动
     * @param id
     */
    @Delete("delete from activity where id = #{id};")
    void deleteById(Integer id);

    /**
     * 修改活动
     * @param activity
     */
    @AutoFill(OperationType.UPDATE)
    void update(Activity activity);
}
