package csim.scu.onlinejudge.dao.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class AbstractUser extends BaseEntity {

    private String account;
    private String password;
    private String name;

}
