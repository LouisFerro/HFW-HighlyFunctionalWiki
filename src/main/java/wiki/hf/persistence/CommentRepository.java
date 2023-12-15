package wiki.hf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.hf.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>
{

}
