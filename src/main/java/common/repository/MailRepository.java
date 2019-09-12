package common.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import common.entity.MailInfoVO;

@Repository
public interface MailRepository extends CrudRepository<MailInfoVO, Integer>{
	public List<MailInfoVO> findAll();
	public List<MailInfoVO> findByIdx(@Param("id") int id);
}
