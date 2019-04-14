package csim.scu.onlinejudge.service.impl;

import csim.scu.onlinejudge.dao.domain.copy.Copy;
import csim.scu.onlinejudge.dao.repository.CopyRepository;
import csim.scu.onlinejudge.dao.repository.base.BaseRepository;
import csim.scu.onlinejudge.service.CopyService;
import csim.scu.onlinejudge.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyServiceImpl extends BaseServiceImpl<Copy, Long> implements CopyService {

    private CopyRepository copyRepository;

    @Autowired
    public CopyServiceImpl(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @Override
    public BaseRepository<Copy, Long> getBaseRepository() {
        return copyRepository;
    }

}
