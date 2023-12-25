package wiki.hf.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;
import wiki.hf.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{

}
