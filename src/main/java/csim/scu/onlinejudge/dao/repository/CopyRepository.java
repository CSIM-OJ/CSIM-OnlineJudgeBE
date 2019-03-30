package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.copy.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

    List<Copy> findByStudentOneAccountOrStudentTwoAccountEquals(String account1, String account2);
}
