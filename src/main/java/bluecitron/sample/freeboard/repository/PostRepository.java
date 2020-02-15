package bluecitron.sample.freeboard.repository;

import bluecitron.sample.freeboard.domain.Category;
import bluecitron.sample.freeboard.domain.Post;
import bluecitron.sample.freeboard.dto.PostDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(Category category, Pageable pageable);
}
