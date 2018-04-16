package cn.edu.gdmec.android.sharepreferencestest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
/**
 * 将用户名和密码保存在私有目录中
 * 缺点：第一次保存用户名和密码后
 * */


public class FileSaveLogin extends Activity implements View.OnClickListener {


    private EditText et_login;
    private EditText et_password;
    private CheckBox renumber_pas;
    private Button login;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mContext=this;
        //取出之前保存的用户名和密码(回显）
        Map<String ,String> map=UserInfoUtil.getUserInfo(mContext);//获取用户名密码；
        if (map!=null){
            String username=map.get("username");
            String password=map.get("password");
            et_login.setText(username);
            et_password.setText(password);
            renumber_pas.setChecked(true);
        }




    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String userName=et_login.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                boolean isSave=renumber_pas.isChecked();
                //1、判断用户名或者密码是否为空
                if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String userInfo=userName+"##"+password;
                //2、判断是否选择保存密码
                if (isSave){
                    boolean result=UserInfoUtil.saveUserInfo(mContext,userInfo);
                    if (result){
                        Toast.makeText(this, "已保存好用户名和密码，记住密码", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "保存失败！", Toast.LENGTH_SHORT).show();
                    }
                }


                //TODO implement
                break;

        }
    }

    private void initView() {
        et_login = (EditText) findViewById(R.id.et_login);
        et_password = (EditText) findViewById(R.id.et_password);
        renumber_pas = (CheckBox) findViewById(R.id.renumber_pas);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);


    }


}
