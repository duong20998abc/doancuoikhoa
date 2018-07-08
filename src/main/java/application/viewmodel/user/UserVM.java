package application.viewmodel.user;

import application.model.UserDetailModel;
import application.viewmodel.common.LayoutHeaderVM;

public class UserVM {
    private UserDetailModel info;

    public UserDetailModel getInfo() {
        return info;
    }

    public void setInfo(UserDetailModel info) {
        this.info = info;
    }
}
