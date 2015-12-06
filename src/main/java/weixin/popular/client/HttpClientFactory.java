package weixin.popular.client;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import weixin.popular.util.HttpConst;

/**
 * httpclient 4.3.x
 * 
 * @author Yi
 * 
 */
public class HttpClientFactory {

	public static HttpClient createHttpClient() {
		try {
			SSLContext sslContext = SSLContexts.custom().useSSL().build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
			poolingHttpClientConnectionManager.setMaxTotal(HttpConst.MAX_TOTAL);
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(HttpConst.MAX_PER_ROUTE);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(HttpConst.CONNECTION_TIMEOUT)
					.setSocketTimeout(HttpConst.SOCKET_TIMEOUT).build();
			return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
					.setConnectionManager(poolingHttpClientConnectionManager)
					.setSSLSocketFactory(sf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HttpClient createHttpClient(int maxTotal, int maxPerRoute) {
		try {
			SSLContext sslContext = SSLContexts.custom().useSSL().build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
			poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(HttpConst.CONNECTION_TIMEOUT)
					.setSocketTimeout(HttpConst.SOCKET_TIMEOUT).build();
			return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
					.setConnectionManager(poolingHttpClientConnectionManager)
					.setSSLSocketFactory(sf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Key store 类型HttpClient
	 * 
	 * @param keystore
	 * @param keyPassword
	 * @param supportedProtocols
	 * @param maxTotal
	 * @param maxPerRoute
	 * @return
	 */
	public static HttpClient createKeyMaterialHttpClient(KeyStore keystore, String keyPassword,
			String[] supportedProtocols, int maxTotal, int maxPerRoute) {
		try {

			SSLContext sslContext = SSLContexts.custom().useSSL()
					.loadKeyMaterial(keystore, keyPassword.toCharArray()).build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,
					supportedProtocols, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
			poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
			return HttpClientBuilder.create()
					.setConnectionManager(poolingHttpClientConnectionManager)
					.setSSLSocketFactory(sf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

}
