package com.mindtree.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.demo.entity.MealPrefrence;

@Repository
public interface MealRepo extends JpaRepository<MealPrefrence, Long>{


	@Query("select m from MealPrefrence m where m.mealType = :mealType")
	MealPrefrence findMealByName(@Param("mealType") String mealType);

	@Transactional
	@Modifying
	@Query("update MealPrefrence m set m.mealType = :mealType where m.mealId=:mealId")
	int updateMealName(@Param("mealId") Long mealId,@Param("mealType") String mealType);

}
