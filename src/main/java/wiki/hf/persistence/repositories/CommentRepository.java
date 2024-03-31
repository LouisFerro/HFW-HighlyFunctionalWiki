// TODO: Implement CommentRepository.

package wiki.hf.persistence.repositories;

import wiki.hf.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
