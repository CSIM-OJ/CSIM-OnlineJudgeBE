package csim.scu.onlinejudge.dao.domain.admin;

import csim.scu.onlinejudge.dao.domain.AbstractUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String account;
    private String password;
    private String name;

    public Admin(String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
    }

}
