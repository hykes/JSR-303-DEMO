package com.github.hykes.xml;

import com.github.hykes.common.JsonResponseException;
import com.google.common.base.Throwables;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-07 13:59:00
 */
@Slf4j
public final class XmlUtil {

    public XmlUtil() {

    }

    /**
     * bean => xml
     * @param obj
     * @return
     */
    public static String marshal(Object obj) {
        StringWriter sw = new StringWriter();

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            marshaller.marshal(obj, sw);
        } catch (JAXBException var4) {
            log.error("marshal failed, cause :{}", Throwables.getStackTraceAsString(var4));
            throw new JsonResponseException("xml serialize failed");
        }
        return sw.toString();
    }

    public static String marshalWithCDATA(Object obj) {
        return "<![CDATA[" + marshal(obj) + "]]>";
    }

    /**
     * xml => bean
     * @param clazz
     * @param xmlData
     * @param <T>
     * @return
     */
    public static <T> T unmarshal(final Class<T> clazz, final String xmlData) {
        if (Strings.isNullOrEmpty(xmlData)) {
            throw new JsonResponseException("xmlData.is.empty");
        }
        try {
            // 通过映射的类创建XMLComtext上下文对象，其中参数为映射的类。
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            // 通过JAXBContext上下文对象创建createUnmarshaller()方法，创建XML转换成JAVA对象的格式。
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // 最后，将XML转换成对映的类，转换后需要强制性转换成映射的类
            InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());
            return (T) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            log.error("unmarshal failed, cause :{}", Throwables.getStackTraceAsString(e));
            throw new JsonResponseException("xml serialize failed, cause:");
        }
    }

}
