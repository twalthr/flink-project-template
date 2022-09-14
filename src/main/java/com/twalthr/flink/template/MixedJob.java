/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twalthr.flink.template;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableDescriptor;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

/** Basic job for running a mixed DataStream API / Table API program. */
public class MixedJob {

  private static final TableDescriptor SOURCE_DESCRIPTOR =
      TableDescriptor.forConnector("datagen")
          .schema(
              Schema.newBuilder()
                  .column("id", DataTypes.BIGINT())
                  .column("name", DataTypes.STRING())
                  .build())
          .option("rows-per-second", "10")
          .option("fields.id.min", "100")
          .option("fields.id.max", "999")
          .option("fields.name.length", "32")
          .build();

  public static void main(String[] args) throws Exception {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    final StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

    final Table sourceTable = tableEnv.from(SOURCE_DESCRIPTOR);

    final DataStream<Row> sourceStream = tableEnv.toChangelogStream(sourceTable);

    sourceStream.print();

    env.execute();
  }
}
