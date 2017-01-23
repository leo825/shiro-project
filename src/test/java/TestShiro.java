import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by LX on 2017/1/23.
 */
public class TestShiro {

    @Test
    public void testBase(){

        SecurityManager manager = new IniSecurityManagerFactory("E:\\WorkSpace\\Git\\shiro-project\\target\\shiro\\WEB-INF\\shiro.ini").getInstance();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("kh","123");

        try{
            subject.login(token);
            Object object = subject.getPrincipal();
            System.out.println(object.toString());
        }catch(UnknownAccountException e){
            System.out.println("用户名不正确");
        }catch(IncorrectCredentialsException e){
            System.out.println("密码不正确");
        }

    }
}
