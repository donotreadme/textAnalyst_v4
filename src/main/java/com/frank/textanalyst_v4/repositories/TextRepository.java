package com.frank.textanalyst_v4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frank.textanalyst_v4.domain.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer> {

	public List<Text> findByTitle(String title);

	public List<Text> findByIsProofRead(boolean isProofed);
	
	@Query("SELECT t.title FROM Text t WHERE t.isProofRead = :isProofed")
	public List<String> findTitleByIsProofRead(@Param("isProofed") boolean isProofed);
}

