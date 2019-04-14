package csim.scu.onlinejudge.manager;


import csim.scu.onlinejudge.common.exception.*;

public interface CommonManager {

    String findUserAuthority(String account, String password) throws EntityNotFoundException;

    int updateUserPassword(String account, String oriPassword, String newPassword, String userType);
}
