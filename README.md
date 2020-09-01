# flink-clickhouse-converter
It is convenient to convert any POJO to clickhouse csv, then insert into ck
```java
[common]
clickhouse_hosts = ip:8123
clickhouse_driver = ru.yandex.clickhouse.ClickHouseDriver

[sink]
sink.timeout_sec = 10
sink.failed_records_path = .
sink.num_writers = 3
sink.num_retries = 5
sink.queue_max_capacity = 10

[local]
sink.target_table_name = database.tableName
sink.max_buffer_size = 5 //批量大小
```
```java
public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRestartStrategy(new RestartStrategies.NoRestartStrategyConfiguration());
        env.setParallelism(1);
        env.getConfig().setGlobalJobParameters(ConfigTool.getClickhouseParamTool());
        env.fromElements(TEST_DATA)
                .map(PojoConverter::convertToCsv)
                .addSink(new ClickhouseSink(ConfigTool.getSinkProperty()));

        env.execute();
    }
```
