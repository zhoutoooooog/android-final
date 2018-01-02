package com.example.tong.androidfinal;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by tong on 2017/12/28.
 */

class NullHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession sslSession) {
        return true;
    }
}
