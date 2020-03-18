package cn.zedongw.bms.utils;

import cn.zedongw.bms.entity.Books;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

public class Dom4jUtilsTest {

    @Test
    public void getList() throws DocumentException, InstantiationException, IllegalAccessException, ParseException {
        /*ArrayList<Users> list = Dom4jUtils.getList(Users.class);
        for (Users users: list) {
            System.out.println(user);
        }*/
        ArrayList<Books> list = Dom4jUtils.getList(Books.class);
        for (Books book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void writeXml() {
//        Dom4jUtils.writeXml(Users.class,null);
        Dom4jUtils.writeXml(Books.class,null);
    }
}