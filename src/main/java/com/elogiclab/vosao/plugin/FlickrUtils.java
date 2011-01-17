package com.elogiclab.vosao.plugin;
/*
 *  The MIT License
 * 
 *  Copyright 2010 Marco Sarti <marco.sarti@gmail.com>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Marco Sarti <marco.sarti@gmail.com>
 */
public class FlickrUtils {

    public static String getLoginUrl(String frob, String apiKey, String apiSecret, String perms) {
        StringBuilder result = new StringBuilder();
        result.append("http://flickr.com/services/auth/?");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add( new BasicNameValuePair("perms", perms) );
        params.add( new BasicNameValuePair("frob", frob));
        params.add( new BasicNameValuePair("api_key", apiKey));

        FlickrUtils.signParams(params, apiSecret);

        String qs = URLEncodedUtils.format(params, "UTF-8");
        result.append(qs);


        return result.toString();
    }



    public static String buildQueryString(Map<String, String> params, String apiSecret) {
        StringBuilder result = new StringBuilder();
        StringBuffer unencoded = new StringBuffer(apiSecret);
        List keyList = new ArrayList(params.keySet());
        Collections.sort(keyList);
        Iterator it = keyList.iterator();
        while (it.hasNext()) {
            if (result.length() > 0) {
                result.append("&");
            }
            String key = it.next().toString();
            result.append(key);
            result.append("=");
            result.append(params.get(key));
            unencoded.append(key);
            unencoded.append(params.get(key));
        }
        System.out.println(unencoded);

        String sig = digest(unencoded.toString());
        result.append("&api_sig=");
        result.append(sig);
        return result.toString();

    }

    private static String digest(String text)  {
        MessageDigest algorithm = null;
        StringBuilder sig = new StringBuilder();
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException exc) {
            throw new RuntimeException(exc);
        }
        algorithm.reset();
        algorithm.update(text.getBytes());
        byte messageDigest[] = algorithm.digest();
        for (int i = 0; i < messageDigest.length; i++) {
            String c = Integer.toHexString(0xFF & messageDigest[i]);
            if (c.length() == 1) {
                sig.append("0");
            }
            sig.append(c);
        }
        return sig.toString();
    }


    public static void signParams(List<NameValuePair> params, String apiSecret) {
        Collections.sort(params, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair t, NameValuePair t1) {
                return t.getName().compareTo(t1.getName());
            }
        });
        StringBuilder stringToEncode = new StringBuilder();
        stringToEncode.append(apiSecret);
        for (NameValuePair nameValuePair : params) {
            stringToEncode.append(nameValuePair.getName());
            stringToEncode.append(nameValuePair.getValue());
        }
        System.out.println(stringToEncode);
        String stringEncoded = digest(stringToEncode.toString());
        params.add(new BasicNameValuePair("api_sig", stringEncoded));
    }

}
