package common.mapper;

import java.util.List;

import common.person.DeviceStatus;
import common.person.PersonDto;
import common.person.Pooltask;

public interface PersonServiceMapper {
	
	public List<PersonDto> getPersonList() throws Exception ;
	public List<PersonDto> detailPerson(int id) throws Exception;
	public int insertPerson(PersonDto person) throws Exception ;
	public int updatePerson(PersonDto person) throws Exception ;
	public int deletePerson(int id) throws Exception ;
	public void onCheckThread(Pooltask pool) throws Exception;
	public void onCheckMonitoring(DeviceStatus Status) throws Exception; 
	//@Async("PersonExecutor")
	//public void Executor() throws Exception;
}
