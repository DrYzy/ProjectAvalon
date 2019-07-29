package com.zero.avaloan.avalongate.mvp.view;
import com.zero.avaloan.avalongate.net.ServerException;

public class LoginContract {

    public interface View {

        void setLoading(boolean v);

        void callback(String contentStr);

        void errorCallback(ServerException e);

    }

    public interface Presenter {

        void login(String name, String password);

    }


}
