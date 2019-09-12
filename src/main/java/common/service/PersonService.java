package common.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.mapper.PersonServiceMapper;
import common.person.PersonDto;


public class PersonService {
	
	/*@Autowired
	PersonServiceMapper personMapper;
	
	//@Inject
	//SqlSession sqlSession;

	@Override
	public List<PersonDto> getPersonList() throws Exception {
		// TODO Auto-generated method stub
		List<PersonDto> per = personMapper.getPersonList();
		
		// Console로 확인을 위한 코드.
		for(int i=0;i<per.size();i++){
			System.out.println("===================" + per.get(i).getName());	
		}
		return (List<PersonDto>) per;
	}

	@Override
	public int insertPerson(PersonDto person) throws Exception {
		// TODO Auto-generated method stub
		return personMapper.insertPerson(person);
	}

	@Override
	public void updatePerson(PersonDto person) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deletePerson(int id) throws Exception {
		return personMapper.deletePerson(id);
	}

	@Override
	public List<PersonDto> detailPerson(int id) throws Exception {
		// TODO Auto-generated method stub
		return personMapper.detailPerson(id);
	}
*/
}
