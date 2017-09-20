package org.code.http;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import org.code.http.constant.ExceptionCode;
import org.code.http.exception.BusinessException;
import sun.nio.ch.IOUtil;

import java.io.IOException;

public class HttpTools {

    private final static Logger logger = Logger.getLogger(IOUtil.class);

    private static HttpTools instance = new HttpTools();

    /**
     * HttpClient实例
     */
    private DefaultHttpClient httpClient = getHttpClient();

    /**
     * http的header中的content-type属性的名字
     */
    private static final String CONTENT_TYPE_NAME = "Content-Type";

    /**
     * http的header中的content-type属性的字符编码
     */
    private static final String UTF_8 = "UTF-8";

    /**
     * http的header中的content-type属性的内容
     */
    private static final String CONTENT_TYPE_VALUE_JSON_UTF_8 = "application/json;charset=UTF-8";

    /**
     * 链接的超时数,默认为5秒,此处要做成可配置（单位为秒）
     */
    private static final int CONNECTION_TIME_OUT = 500;

    /**
     * 连接最大的超时时间
     */
    private static final int DEFULT_SO_TIMEOUT = 30;

    /**
     * 每个主机的最大并行链接数，默认为2
     */
    private static final int MAX_CONNECTIONS_PER_HOST = 10;

    /**
     * 客户端总并行链接最大数，默认为20
     */
    private static final int MAX_TOTAL_CONNECTIONS = 20;

    private HttpTools() {

    }

    public static HttpTools getInstance() {
        return instance;
    }

    /**
     * 构造Http客户端对象 <功能详细描述>
     *
     * @return [参数说明]
     *
     * @return HttpClient [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public DefaultHttpClient getHttpClient() {
        // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
        HttpParams params = new BasicHttpParams();
        // 设置超时时间
        int maxConnectionTimeout = CONNECTION_TIME_OUT * 1000;

        params.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT,
                maxConnectionTimeout);

        // 设定参数：读取超时时间
        int soTimeout = DEFULT_SO_TIMEOUT * 1000;
        params.setParameter(HttpConnectionParams.SO_TIMEOUT, soTimeout);

        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params,
                HTTP.DEF_CONTENT_CHARSET.name());
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        HttpProtocolParams.setUserAgent(params,
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit");

        // 此处运用连接池技术。
        PoolingClientConnectionManager manager = new PoolingClientConnectionManager();

        // 设定参数：客户端的总连接数
        int maxTotalConnections = MAX_TOTAL_CONNECTIONS;
        manager.setMaxTotal(maxTotalConnections);
        // 设定参数：与每个主机的最大连接数
        int maxConnectionsI = MAX_CONNECTIONS_PER_HOST;
        manager.setDefaultMaxPerRoute(maxConnectionsI);

        DefaultHttpClient httpClient = new DefaultHttpClient(manager, params);

        return httpClient;
    }

    /**
     * 发送http请求，并返回响应的json报文 <功能详细描述>
     *
     * @param url
     *            http请求url
     * @param json
     *            请求json报文
     * @return
     * @throws BusinessException
     *             [参数说明]
     *
     * @return String [返回类型说明]json报文
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String sendHttpRequest(String url, String json)
            throws BusinessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter sendHttpRequest()! url = " + url
                    + " and \n json = " + json);
        }

        HttpPost httppost = new HttpPost(url);

        try {
            // 设置header信息，传输XML格式的
            httppost.addHeader(new BasicHeader(CONTENT_TYPE_NAME,
                    CONTENT_TYPE_VALUE_JSON_UTF_8));
            httppost.addHeader(new BasicHeader("Accept",
                    "text/html,application/xhtml+json,application/json;q=0.9,*/*;q=0.8"));

            if(StringUtils.isNotEmpty(json))
            {
                // 发送含xml消息体的对象
                StringEntity entity = new StringEntity(json, UTF_8);
                entity.setContentType(CONTENT_TYPE_VALUE_JSON_UTF_8);
                httppost.setEntity(entity);
            }

            // 处理响应结果码
            HttpResponse response = httpClient.execute(httppost);
            ;
            int resultCode = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK != resultCode) {
                logger.error("The response code is error! resuleCode = "
                        + String.valueOf(resultCode) + " and url = " + url
                        + " and json = " + json);

                throw new BusinessException(
                        ExceptionCode.HTTP_RESPONSE_CODE_IS_NOT_OK,
                        "The response code is error! resuleCode = "
                                + String.valueOf(resultCode) + " and url = "
                                + url + " and json = " + json);
            }

            // 获取响应报文
            String responseJsonStr = new String(
                    EntityUtils.toByteArray(response.getEntity()), UTF_8);

            if (logger.isDebugEnabled()) {
                logger.debug("Exit sendHttpRequest()! url = " + url
                        + " and response json = " + responseJsonStr);
            }

            return responseJsonStr;
        } catch (Exception ex) {
            logger.error("send http request error! " + ex.toString(), ex);

            throw new BusinessException(ExceptionCode.SEND_HTTP_REQUEST_ERROR,
                    ex);
        } finally {
            if (null != httppost) {
                httppost.releaseConnection();
            }
        }

    }


    /**
     * 发生Get请求
     *
     * @param url 请求url
     * @return
     * @throws BusinessException [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String sendHttpRequestByGet(String url) throws BusinessException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Enter sendHttpRequest()! url = " + url);
        }

        HttpGet getMethod = null;

        try
        {
            getMethod = new HttpGet(url);

            // 设置header信息，传输日志相关参数
//            ScoutBean scoutBean = ScoutLogTheadLocal.get();
//            if (null != scoutBean)
//            {
//                getMethod.addHeader(new BasicHeader(USER_ID, scoutBean.getMsisdn()));
//                getMethod.addHeader(new BasicHeader(WAP_SOURCE_IP, scoutBean.getPortalIp()));
//                getMethod.addHeader(new BasicHeader(SESSION_ID, scoutBean.getSessionId()));
//                getMethod.addHeader(new BasicHeader(SEQUENCE, scoutBean.getSequence()));
//                getMethod.addHeader(new BasicHeader(PORTAL_TYPE, scoutBean.getPortalType()));
//            }

            HttpResponse response = httpClient.execute(getMethod);
            int resultCode = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK != resultCode) {
                logger.error("The response code is error! resuleCode = "
                        + String.valueOf(resultCode) + " and url = " + url);

                throw new BusinessException(
                        ExceptionCode.HTTP_RESPONSE_CODE_IS_NOT_OK,
                        "The response code is error! resuleCode = "
                                + String.valueOf(resultCode) + " and url = "
                                + url );
            }

            // 获取响应报文
            String responseJsonStr = new String(
                    EntityUtils.toByteArray(response.getEntity()), UTF_8);

            if (logger.isDebugEnabled()) {
                logger.debug("Exit sendHttpRequest()! url = " + url
                        + " and response json = " + responseJsonStr);
            }

            return responseJsonStr;
        }
        catch (IOException ex)
        {
            logger.error("send http request error! " + ex.toString(), ex);

            throw new BusinessException(ExceptionCode.SEND_HTTP_REQUEST_ERROR,
                    ex);
        }
        finally
        {
            if (null != getMethod)
            {
                getMethod.releaseConnection();
            }
        }
    }
}
