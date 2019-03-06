package top.hihuzi.collection.common;

import org.junit.Test;
import top.hihuzi.collection.sql.Son;
import top.hihuzi.collection.utils.ParamUtils;

import javax.sound.midi.SoundbankResource;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PublicMethodTest {

    @Test
    public void fields() {

        Son son = new Son();
        String[] fields = ParamUtils.fields(son);
        String[] field2 = ParamUtils.fields(new Son());
        String[] field3 = ParamUtils.fields("top.hihuzi.collection.sql.Son");
        String[] field4 = ParamUtils.fields(Son.class);
        System.out.println(Arrays.asList(fields).toArray());
        System.out.println(Arrays.asList(field2).toArray());
        System.out.println(Arrays.asList(field3).toArray());
        System.out.println(Arrays.asList(field4).toArray());
    }

}