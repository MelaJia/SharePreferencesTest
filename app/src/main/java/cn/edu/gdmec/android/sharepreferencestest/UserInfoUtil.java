package cn.edu.gdmec.android.sharepreferencestest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2018/4/10.
 */

public class UserInfoUtil {
    public static boolean saveUserInfo(String userInfo){
        try{
            File file=new File("/data/data/cn.edu.gdmec.android.sharepreferencestest/","a.txt");
            FileOutputStream fos= new FileOutputStream(file,true);
            fos.write(userInfo.getBytes());


            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }

          return true;
    }
    //封装用户名和密码
    public static Map<String ,String> getUserInfo(){
        try{
            File file=new File("/data/data/cn.edu.gdmec.android.sharepreferencestest/","a.txt");
            FileInputStream fis= new FileInputStream(file);
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String[] info=br.readLine().split("##");
            HashMap<String ,String> hashMap=new HashMap<String ,String>();
            hashMap.put("username",info[0]);
            hashMap.put("password",info[1]);
            br.close();
            fis.close();
            return hashMap;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;


    }
}
