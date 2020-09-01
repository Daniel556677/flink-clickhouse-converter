package com.gree.iot7.sink;

import cn.hutool.setting.Setting;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import ru.ivi.opensource.flinkclickhousesink.model.ClickhouseClusterSettings;
import ru.ivi.opensource.flinkclickhousesink.model.ClickhouseSinkConsts;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ConfigTool {
    private static final Properties properties = new Properties();
    private static Setting setting;
    private static Map<String, String> params;
    private static ParameterTool parameterTool;

    static {
        setting = new Setting("clickhouse.setting");
        params = new HashMap<>();
        properties.put(ClickhouseSinkConsts.TARGET_TABLE_NAME, setting.get("local", "sink.target_table_name"));
        properties.put(ClickhouseSinkConsts.MAX_BUFFER_SIZE, setting.get("local", "sink.max_buffer_size"));
        params.put(ClickhouseClusterSettings.CLICKHOUSE_HOSTS, setting.get("common", "clickhouse_hosts"));
        params.put(ClickhouseSinkConsts.TIMEOUT_SEC, setting.get("sink", "sink.timeout_sec"));
        params.put(ClickhouseSinkConsts.FAILED_RECORDS_PATH, setting.get("sink", "sink.failed_records_path"));
        params.put(ClickhouseSinkConsts.NUM_WRITERS, setting.get("sink", "sink.num_writers"));
        params.put(ClickhouseSinkConsts.NUM_RETRIES, setting.get("sink", "sink.num_retries"));
        params.put(ClickhouseSinkConsts.QUEUE_MAX_CAPACITY, setting.get("sink", "sink.queue_max_capacity"));
        parameterTool = ParameterTool.fromMap(params);
    }

    public static Properties getSinkProperty() {
        return properties;
    }

    public static ParameterTool getClickhouseParamTool() {
        return parameterTool;
    }
}
