package cn.edu.gdmec.android.sharepreferencestest;

import android.content.Context;
import android.os.Environment;

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
    public static boolean saveUserInfo(Context context,String userInfo){
       // String userInfo=userName+"##"+password;
            try{
         //   String path="/data/data/cn.edu.gdmec.android.sharepreferencestest/";
            //通过Context对象获取私有目录路径
            String path=context.getFilesDir().getPath();
            //  String path= Environment.getExte.rnalStorageDirectory().getPath();
            FileOutputStream  fileOutputStream= context.openFileOutput("a.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(userInfo.getBytes());
            fileOutputStream.close();
//                String path=context.getFilesDir().getPath();
//            File file=new File(path,"a.txt");
//            FileOutputStream fos= new FileOutputStream(file,true);
//            fos.write(userInfo.getBytes());
//            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }

          return true;
    }
    //封装用户名和密码
    public static Map<String ,String> getUserInfo(Context context){
        try{
            String path=context.getFilesDir().getPath();
            FileInputStream  fileInputStream= context.openFileInput("a.txt");
//            File file=new File(path,"a.txt");
//            FileInputStream fis= new FileInputStream(file);
            BufferedReader br=new BufferedReader(new InputStreamReader(fileInputStream));
            String[] info=br.readLine().split("##");
            HashMap<String ,String> hashMap=new HashMap<String ,String>();
            hashMap.put("username",info[0]);
            hashMap.put("password",info[1]);
            br.close();
          //  fis.close();
            fileInputStream.close();
            return hashMap;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;


    }
}
