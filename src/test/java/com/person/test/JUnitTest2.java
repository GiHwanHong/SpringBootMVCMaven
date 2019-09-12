package com.person.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import common.entity.MailInfoVO;
import common.repository.MailRepository;

public class JUnitTest2 {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	MailRepository mailRepository;
	
	@Test
	public void jpaTest() {
				
	}
	
}
