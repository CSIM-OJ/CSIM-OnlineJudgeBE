package csim.scu.onlinejudge.dao;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public interface BaseDao {

    public void save();

    public void find();

    public void delete();
}
