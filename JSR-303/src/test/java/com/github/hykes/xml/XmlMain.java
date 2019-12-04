package com.github.hykes.xml;

import com.github.hykes.xml.WYCGC1.Detail;
import com.github.hykes.xml.WYCGC1.Item;
import com.google.common.collect.Lists;
import java.math.BigDecimal;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 15:55:00
 */
public class XmlMain {

    public static void main(String[] args) {

        WYCGC1 wy = new WYCGC1();
        wy.setTag("bid");
        wy.setBizId("1");

//        WYCGC1.Item item1 = new Item();
//        item1.setName("细项1");
//        item1.setQuantity(BigDecimal.ONE);
//        WYCGC1.Item item2 = new Item();
//        item2.setName("细项1");
//        item2.setQuantity(BigDecimal.ONE);

//        WYCGC1.Detail detail = new Detail();
//        detail.setItems(Lists.newArrayList(item1, item2));
//        wy.setDetail(detail);

        System.out.println(XmlUtil.marshalWithCDATA(wy));
    }

}
