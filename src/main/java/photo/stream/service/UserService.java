package photo.stream.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import photo.stream.model.vo.UserVo;

/**
 * @author xuhf
 * @date 2022/10/9
 */
@Service
@RequiredArgsConstructor
public class UserService {


    /**
     * 获取当前用户
     * @return current user
     */
    public UserVo getCurrentUser() {
        return new UserVo();
    }

}
