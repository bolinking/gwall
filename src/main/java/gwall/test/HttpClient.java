package gwall.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * http ������ʹ���
 *
 * @author wonderful.
 * @version 3.0.0
 */
public class HttpClient {

    private final static Logger LOGGER = Logger.getLogger(HttpClient.class);
    private String method = null;
    private String CONTENT_TYPE = null; //Ĭ��
    
    /**
     * Ĭ��POST
     *
     * @return String
     */
    public String getMethod() {
        if (method == null) {
            return "POST";
        }
        return method;
    }

    /**
     *  POST �� GET 
     *
     * @param method .
     */
    public void setMethod(String method) {
        this.method = method;
    }
    
    /**
     * ִ�е��� serviceURL��ַ ����
     * @param serviceURL webService��ַ.
     * @param param String.
     * @param ContentType �������ʵ���Ӧ��MIME��Ϣ   �磺Content-Type: application/x-www-form-urlencoded,application/json,application/xml  
     * @return String.
     */
    public String pub(String serviceURL, String param,String contentType) {
        CONTENT_TYPE = contentType;
        return pub(serviceURL,param);
    }
    /**
     * ִ�е��� serviceURL��ַ ����
     * @param serviceURL webService��ַ.
     * @param param String.
     * @return String.
     */
    public String pub(String serviceURL, String param) {
        URL url = null;
        HttpURLConnection connection = null;
        StringBuffer buffer = new StringBuffer();
        LOGGER.debug("request:" + serviceURL + "?" + param);
        try {
            url = new URL(serviceURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(getMethod());
            connection.setConnectTimeout(5000);//30������
            connection.setReadTimeout(5*60*1000);//5���Ӷ�����
            connection.setRequestProperty("Content-Length", param.length() + "");
            if(CONTENT_TYPE != null){
                connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            }           
            
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(param.getBytes("UTF-8"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
        } catch (IOException e) {
            LOGGER.debug(null, e);
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        LOGGER.debug("response:" + buffer.toString());
        return buffer.toString();
    }
}

