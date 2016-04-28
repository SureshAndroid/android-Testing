package app.testingappnew;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by ASUS on 21-Apr-16.
 */
public class LocalFile {
String FOLDER_NAME = "MyTestApp";
    public void savedToExternal(Context context, String content,String filename)  {
        FileOutputStream fos=null;
        Writer wrt = null;

        try
        {
            File file = new File(makeFolders(filename),filename);
            fos =  new FileOutputStream(file);
            wrt = new OutputStreamWriter(fos, "UTF-8" );

            wrt.write(content);
            wrt.flush();
            Log.e("File root path", "" + file.getAbsolutePath());
            Toast.makeText(context,"File root path" + file.getAbsolutePath(),Toast.LENGTH_SHORT).show();

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }
    public String loadfromexternal(Context context,String filename)
    {
        String res = null;
        File file = new File(makeFolders(filename),filename);
        if(!file.exists())
        {
            Log.e("", "File" + file.getAbsolutePath() + "Not Found ...");
            Toast.makeText(context, "File"+file.getAbsolutePath()+ "Not Found ...", Toast.LENGTH_SHORT).show();
            return null;
        }
        FileInputStream fis=null;
        BufferedReader inputReader = null;

        try {
            fis = new FileInputStream(file);
            inputReader = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line=inputReader.readLine())!=null)
            {
                strBuilder.append(line+"\n");

            }
            res=strBuilder.toString();
        } catch (Throwable e) {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException ignored) {}
            }
            if(inputReader!= null){
                try {
                    inputReader.close();
                } catch (IOException ignored) {}
            }
        }

        return res;

    }

    public File makeFolders(String folder_name) {
        String root = Environment.getExternalStorageDirectory().toString();
        File baseDir = new File(root + "/"+FOLDER_NAME);

  /*      //File baseDir;
        if (android.os.Build.VERSION.SDK_INT < 8) {
            baseDir = Environment.getExternalStorageDirectory();
        } else {
//            baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            baseDir = Environment.getExternalStorageDirectory();
        }
*/
        if (baseDir == null)
            return Environment.getExternalStorageDirectory();
        Log.e("Local folder:", "" + baseDir.getAbsolutePath());
        File fileFolder = new File(baseDir +"/"+folder_name);
        if (fileFolder.exists())
            return fileFolder;
        if (fileFolder.mkdirs())
            return fileFolder;
        return Environment.getExternalStorageDirectory();
    }
}
