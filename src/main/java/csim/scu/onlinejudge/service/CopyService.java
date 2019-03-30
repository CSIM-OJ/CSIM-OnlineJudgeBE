package csim.scu.onlinejudge.service;

import csim.scu.onlinejudge.dao.domain.copy.Copy;

import java.util.List;

public interface CopyService {

    void saveAll(List<Copy> copys);
}
