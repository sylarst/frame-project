package network;

import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.age.mac.frameproject.bean.LoginBean;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mac on 2019/7/16.
 */

public interface APIService{

    /**
     * 登陆
     * @return
     */
    @POST("member/login/loginByPass")
    Flowable<BaseObjectBean<LoginBean>> login(@Body JSONObject params);
}
