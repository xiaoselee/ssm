package cn.test.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class GenerateKeyUtil {
	 private static final Logger log = Logger.getLogger(GenerateKeyUtil.class);  
	    /** 
	     * @param t 
	     * @param parm 
	     * @return 
	     * @throws NoSuchAlgorithmException  
	     * 生成key供redis入库 
	     **/  
	    @SuppressWarnings("static-access")  
	    public synchronized static String generateKey(Thread t,String parms){  
	        StringBuffer key = new StringBuffer();  
	        String resultKey = null;  
	        key.append(t.currentThread().getStackTrace()[1].getClassName())  
	        .append(t.currentThread().getStackTrace()[1].getMethodName())  
	        .append(t.currentThread().getStackTrace()[1].getLineNumber())  
	        .append(parms);  
	        MessageDigest md;  
	        try {  
	            md = MessageDigest.getInstance("md5");  
	            byte md5[] = md.digest(key.toString().getBytes());  
	            //base64编码--任意二进制编码明文字符  
	            Base64 encoder = new Base64();  
	            resultKey = encoder.encodeAsString(md5);              
	        } catch (NoSuchAlgorithmException e) {  
	            log.error(e.getMessage(),e);  
	        }  
	        return resultKey;  
	    }   
	    /** 
	     * @param t 
	     * @param parm 
	     * @return 
	     * @throws NoSuchAlgorithmException  
	     * 生成key供redis入库 
	     **/      
	    public synchronized static String generateKey(Thread t){  
	        return generateKey(t,"");  
	    }     
}
