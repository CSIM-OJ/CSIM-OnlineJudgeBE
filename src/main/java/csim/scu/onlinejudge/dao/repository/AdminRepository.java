package csim.scu.onlinejudge.dao.repository;

import csim.scu.onlinejudge.dao.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByAccount(String account);
    Optional<Admin> findByAccount(String account);
    @Modifying(clearAutomatically = true)
    @Query("update Admin set password=:newPassword where account=:account AND password=:oriPassword")
    int updatePasswordByAccountAndPassword(@Param("account")String account,
                                           @Param("oriPassword")String oriPassword,
                                           @Param("newPassword")String newPassword);
}