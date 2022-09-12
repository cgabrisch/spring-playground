package de.cgabrisch.spring.xmlconfig;

import java.io.UnsupportedEncodingException;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class XmlConfigurationMain {
    private static String BEANS_XML = """
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
            <bean name="helloWorld hwAlias" class="de.cgabrisch.spring.xmlconfig.HelloWorldBean" />
        </beans>
            """;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Resource beansXml = new ByteArrayResource(BEANS_XML.getBytes("UTF8"));

        try (GenericXmlApplicationContext appContext = new GenericXmlApplicationContext(beansXml)) {
            var helloWorldBean = appContext.getBean("helloWorld", HelloWorldBean.class);
            var hwAliasBean = appContext.getBean("hwAlias", HelloWorldBean.class);

            helloWorldBean.helloWorld();
            System.out.println(String.format("Aliased bean is identical: %b", helloWorldBean == hwAliasBean));
        }
    }

}
