package bluecitron.sample.freeboard.repository;

import bluecitron.sample.freeboard.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
