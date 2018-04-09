package cn.edu.gdmec.android.sharepreferencestest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText et_login;
    private EditText et_password;
    private Button login;
    private CheckBox rememberPsw;
    private SharedPreferences sp;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        et_login = (EditText) findViewById(R.id.et_login);
        et_password = (EditText) findViewById(R.id.et_password);
        rememberPsw = (CheckBox) findViewById(R.id.renumber_pas);
        mContext=this;
        boolean isRemember = sp.getBoolean("remember_pas",false);
        if (isRemember){
            String userName=sp.getString("userName","");
            String password=sp.getString("password","");
            et_login.setText(userName);
            et_password.setText(password);
            rememberPsw.setChecked(true);
        }
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=et_login.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
                    Toast.makeText(mContext, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

                }
                //如果账号是“a”,密码是“a"，则登录成功
                if (userName.equals("a")&&password.equals("a")){
                    SharedPreferences.Editor editor=sp.edit();
                    if (rememberPsw.isChecked()){
                        editor.putBoolean("remember_pas",true);
                        editor.putString("userName",userName);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                   Toast.makeText(mContext, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show();
                   //Toast.makeText(LoginActivity.this, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
