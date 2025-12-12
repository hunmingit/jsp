package org.joonzis.service; //서비스 -> 다오 -> 매퍼 -> db

import java.util.List;
import java.util.Map;

import org.joonzis.vo.EmployeeVO;

public interface EmployeeService {
	public List<EmployeeVO> getAll();
	public List<EmployeeVO> getUserInfoByDept(int deptId); //쿼리를 실행하는데 필요한 매개변수 넣어줌
	public List<EmployeeVO> getDynamic(Map<String, String> map);
}
