package cn.edu.gdmec.android.sharepreferencestest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText et_login;
    private EditText et_password;
    private Button login;
    private CheckBox remenmberPas;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        et_login = (EditText) findViewById(R.id.et_login);
        et_password = (EditText) findViewById(R.id.et_password);
        remenmberPas = (CheckBox) findViewById(R.id.renumber_pas);
        boolean isRemember = sp.getBoolean("remember_pas",false);
        if (isRemember){
            String userName=sp.getString("userName","");
            String password=sp.getString("password","");
            et_login.setText(userName);
            et_password.setText(password);
            remenmberPas.setChecked(true);
        }
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=et_login.getText().toString();
                String password=et_password.getText().toString();
                //如果账号是“a”,密码是“a"，则登录成功
                if (userName.equals("a")&&password.equals("a")){
                    SharedPreferences.Editor editor=sp.edit();
                    if (remenmberPas.isChecked()){
                        editor.putBoolean("remember_pas",true);
                        editor.putString("userName",userName);
                        editor.putString("userName",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
