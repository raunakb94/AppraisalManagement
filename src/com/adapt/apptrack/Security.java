package com.adapt.apptrack;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*Contains Functions that are used for Security Like 
 * Hash Functions Of any String Validate list of Users
*/
class Security {
	
	/*
	 * This Function Generates The Hash Of any String Supplied As an argument str
	 */
	public String generateHash(String str)
	{
		String md5 = null;
        
        if(null == str) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update str string in message digest
        digest.update(str.getBytes(), 0, str.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
		
	}
	

}
