package com.gree.iot7.sink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class PojoConverter<T> implements Serializable {

    public static <T> String convertToCsv(T book) {
        String bookStr = book.toString();
        JSONObject object = JSON.parseObject(bookStr, Feature.OrderedField);
        log.info("=====: {}", object);
        Set<String> keys = object.keySet();
        StringBuilder builder = new StringBuilder();
        builder.append("( ");
        Iterator<String> iterator = keys.iterator();
        iterator.forEachRemaining(key -> {
            Object tmp = object.get(key);
            if (tmp instanceof String) {
                builder.append("'");
                builder.append(tmp);
                builder.append("', ");
            } else {
                builder.append(String.valueOf(object.get(key)));
                builder.append(", ");
            }
        });
        builder.deleteCharAt(builder.lastIndexOf(",")).append(" )");
        log.info("-----: {}", builder.toString());
        return builder.toString();
    }
}
