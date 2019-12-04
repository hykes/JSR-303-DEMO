package com.github.hykes;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 15:55:00
 */
public class ApplicationTests {

    public static void main(String[] args) {
        System.out.println(ItemType.APPROVE_ING.getValue());
        System.out.println(ItemType.APPROVE_ING.getDescription());
        System.out.println(ItemType.APPROVE_ING.name());
        System.out.println(ItemType.valueOf("APPROVE_ING").getDescription());
        System.out.println(ItemStatus.APPROVE_ING.id());
        System.out.println(ItemStatus.APPROVE_ING.code());

    }

}
