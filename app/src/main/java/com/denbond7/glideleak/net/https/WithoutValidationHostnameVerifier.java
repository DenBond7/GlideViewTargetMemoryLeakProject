package com.denbond7.glideleak.net.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author Denis Bondarenko
 *         Date: 10.12.2014
 *         Time: 14:15
 *         E-mail: DenBond7@gmail.com
 */
public class WithoutValidationHostnameVerifier implements HostnameVerifier {

  @Override
  public boolean verify(String hostname, SSLSession session) {
    return true;
  }
}
