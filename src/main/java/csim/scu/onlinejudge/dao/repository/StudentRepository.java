package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.course.Course;
import csim.scu.onlinejudge.dao.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByAccount(String account);
    Optional<Student> findByAccount(String account);
    Optional<Student> findByName(String name);
    @Modifying(clearAutomatically = true)
    @Query("update Student set password=:newPassword where account=:account AND password=:oriPassword")
    int updatePasswordByAccountAndPassword(@Param("account")String account,
                                 @Param("oriPassword")String oriPassword,
                                 @Param("newPassword")String newPassword);
}
