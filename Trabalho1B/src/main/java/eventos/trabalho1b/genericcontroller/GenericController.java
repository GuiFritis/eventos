package eventos.trabalho1b.genericcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class GenericController {
    
    private boolean needLogin;

    public GenericController(boolean soExecuteComLogin) {
        this.needLogin = soExecuteComLogin;
    }
    
    public abstract void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public boolean isNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(boolean soExecuteComLogin) {
        this.needLogin = soExecuteComLogin;
    }

    

    
    
        
}
