package bluecitron.sample.freeboard.repository;

import bluecitron.sample.freeboard.domain.Category;
import bluecitron.sample.freeboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCategory(Category category, Pageable pageable);
}
