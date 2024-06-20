package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
