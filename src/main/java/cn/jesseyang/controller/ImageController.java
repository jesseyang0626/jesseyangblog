package cn.jesseyang.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@Controller
@RequestMapping(value="/img")
public class ImageController {
	//设置好账号的ACCESS_KEY和SECRET_KEY  
    String ACCESS_KEY = "xcK0SB8ZDVnEuAfLNjLyaFDgIiFbIrhIdNHwnec2"; //这两个登录七牛 账号里面可以找到  
    String SECRET_KEY = "L0-L0GXQebg-qubb1b2UMW4jPMBNO0WnkTPRKDar";  
	
    //url
    String url = "http://oolexlgxs.bkt.clouddn.com/";
    //要上传的空间  
    String bucketname = "jesseyang"; //填写新建的那个存储空间对象的名称
    //上传到七牛后保存的文件名  
    String key = "02.jpg";    
    //上传文件的路径  
 //   String FilePath = "f:\\3419778519672598.png";  //本地要上传文件路径  
    //密钥配置  
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);  
    //创建上传对象  
    UploadManager uploadManager = new UploadManager(new Configuration());  
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了  
    public String getUpToken(){  
        return auth.uploadToken(bucketname);  
    }  

	@RequestMapping(value="/upload",method =
		{ RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam MultipartFile file,HttpServletRequest hsr,HttpServletResponse res){
		System.out.println("calld upload");
		byte[] buffer = null;  
        try  
        {  
        	key=file.getOriginalFilename();
            InputStream fis = file.getInputStream();  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1)  
            {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }  
        catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
		System.out.println("calld ok");
		
		
			try {  
		        //调用put方法上传  
		        Response ress = uploadManager.put(buffer, key, getUpToken());  
		        //打印返回的信息  
				System.out.println(ress.isOK());
				System.out.println(ress.bodyString());   
	        } catch (QiniuException e) {  
	            Response r = e.response;  
	            // 请求失败时打印的异常的信息  
	            System.out.println(r.toString());  
	            try {  
	                //响应的文本信息  
	              System.out.println(r.bodyString());  
	            } catch (QiniuException e1) {  
	                //ignore  
	            }  
	        } 
		return url+file.getOriginalFilename(); 
			
	}
		/*String filePath = "";
		try {
			String path = hsr.getServletContext().getRealPath("");
			System.out.println(hsr.getServletContext().getRealPath(""));
			File file2 = new File(path+"img",file.getOriginalFilename());
			filePath = path+"img\\"+file.getOriginalFilename();
			System.out.println(file2.getAbsolutePath());
			key = file.getOriginalFilename();
//			 if(!file2.exists()){  
//		            file2.mkdirs();  
//		        }  
			file.transferTo(file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("calld ok");
		
		
		try {  
	        //调用put方法上传  
	        Response ress = uploadManager.put(filePath, key, getUpToken());  
	        //打印返回的信息  
			System.out.println(ress.isOK());
			System.out.println(ress.bodyString());   
	        } catch (QiniuException e) {  
	            Response r = e.response;  
	            // 请求失败时打印的异常的信息  
	            System.out.println(r.toString());  
	            try {  
	                //响应的文本信息  
	              System.out.println(r.bodyString());  
	            } catch (QiniuException e1) {  
	                //ignore  
	            }  
	        } 
		return url+file.getOriginalFilename(); */
		

	
	
	  //普通上传    测试方法
    public void upload() throws IOException{  
	      try {  
	        //调用put方法上传  
	        Response res = uploadManager.put("", key, getUpToken());  
	        //打印返回的信息  
			System.out.println(res.isOK());
			System.out.println(res.bodyString());   
	        } catch (QiniuException e) {  
	            Response r = e.response;  
	            // 请求失败时打印的异常的信息  
	            System.out.println(r.toString());  
	            try {  
	                //响应的文本信息  
	              System.out.println(r.bodyString());  
	            } catch (QiniuException e1) {  
	                //ignore  
	            }  
	        }         
	    }  

	
	
}
