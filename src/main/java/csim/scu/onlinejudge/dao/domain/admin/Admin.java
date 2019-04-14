package csim.scu.onlinejudge.dao.domain.admin;

import csim.scu.onlinejudge.dao.domain.base.AbstractUser;
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
public class Admin extends AbstractUser {

    public Admin(String account, String password, String name) {
        super(account, password, name);
    }

}
