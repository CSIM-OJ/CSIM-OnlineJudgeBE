package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByAccount(String account);
    Optional<Teacher> findByAccount(String account);
    Optional<Teacher> findByName(String name);
    @Modifying(clearAutomatically = true)
    @Query("update Teacher set password=:newPassword where account=:account AND password=:oriPassword")
    int updatePasswordByAccountAndPassword(@Param("account")String account,
                                           @Param("oriPassword")String oriPassword,
                                           @Param("newPassword")String newPassword);

}
