package com.frank.textanalyst_v4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frank.textanalyst_v4.domain.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer>{
	 
	public List<Text> findByTitle(String title);
	public List<Text> findByIsProofRead(boolean isProofed);
}
