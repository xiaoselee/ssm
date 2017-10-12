package cn.test.core.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.test.core.task.entity.ScheduleJob;

@Mapper
@Repository
public interface TaskDao {
	
	int add(ScheduleJob task);
	
	List<ScheduleJob> selectScheduleJob();

}
