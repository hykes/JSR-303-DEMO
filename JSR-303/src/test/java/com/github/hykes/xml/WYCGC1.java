package com.github.hykes.xml;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * 在类上加上@XmlAccessorType(XmlAccessType.FIELD)注解，加上此注解那么xml的访问类型为成员变量，而不是getter/setter方法对。
 * 该注解每个非静态，非瞬时成员变量将会被自动绑定到xml文档中，除非标@XmlTransient注解。getter/setter方法只有明确标有JAXB注解才会被绑定。
 * 不加@XmlAccessorType(XmlAccessType.FIELD)注解，将@XmlElement注解加到setter或者getter方法上，因为默认的访问类型为getter/setter方法对。
 * 每个public访问权限的getter/sette方法对和公有成员变量才会自动绑定，访问权限为private,protected和缺省访问权限的getter/setter方法对只有明确标有才会自动绑定。
 */
@Data
@XmlRootElement(name = "DATA")
@XmlAccessorType(XmlAccessType.FIELD)
public class WYCGC1 {

//    @XmlAttribute
//    private Integer id;

    /**
     * 岗位代码
     */
    @XmlElement(name = "PALNS")
    private String plans;

    /**
     * 岗位名称
     */
    @XmlElement(name = "XPLLTX")
    private String plansName;

    /**
     * 定义当前业务标识
     * 用于审核回调时的消息 tag
     */
    @XmlElement(name = "SUBFUNCTION")
    private String tag;

    /**
     * 定义当前业务ID
     */
    @XmlElement(name = "JOBORDERID")
    private String bizId;

    /**
     * 组织层级
     * 总部 A ，区域 T ，项目 P
     */
    @XmlElement(name = "DPTYP")
    private String orgLevel;


    @XmlElement(name = "TITLE")
    private String title;


    @XmlElement(name = "DETAIL")
    private Detail detail;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Detail {

        @XmlElement(name = "ITEM")
        private List<Item> items;

    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {

        @XmlElement(name = "NAME")
        private String name;

        @XmlElement(name = "QUANTITY")
        private BigDecimal quantity;

    }

}
