package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.https;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Denis Bondarenko
 *         Date: 10.12.2014
 *         Time: 14:13
 *         E-mail: DenBond7@gmail.com
 */
public class CustomSSLSocketFactory extends SSLSocketFactory {
  public static final String TLS = "TLS";
  private SSLContext sslContext = SSLContext.getInstance(TLS);

  public CustomSSLSocketFactory() throws
      NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
    super();

    TrustManager tm = new X509TrustManager() {
      public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      }

      public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      }

      public X509Certificate[] getAcceptedIssuers() {
        return null;
      }
    };

    sslContext.init(null, new TrustManager[]{tm}, null);
  }

  @Override
  public String[] getDefaultCipherSuites() {
    return new String[0];
  }

  @Override
  public String[] getSupportedCipherSuites() {
    return new String[0];
  }

  @Override
  public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
    return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
  }

  @Override
  public Socket createSocket() throws IOException {
    return sslContext.getSocketFactory().createSocket();
  }

  @Override
  public Socket createSocket(String host, int port) throws IOException {
    return null;
  }

  @Override
  public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
    return null;
  }

  @Override
  public Socket createSocket(InetAddress host, int port) throws IOException {
    return null;
  }

  @Override
  public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws
      IOException {
    return null;
  }
}