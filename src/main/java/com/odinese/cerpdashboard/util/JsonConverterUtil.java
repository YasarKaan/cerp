package com.odinese.cerpdashboard.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Component
public class JsonConverterUtil {


    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode convertJson(String jsonString) throws JsonProcessingException {
        JsonNode root = mapper.readTree(jsonString);
        ObjectNode output = mapper.createObjectNode();

        setHealthStatus(root, output);
        setMonitorsInfo(root, output);
        setScrubStatus(root, output);
        setOsdStatus(root, output);
        setPgInfo(root, output);
        setPoolsInfo(root, output);
        setDiskUsage(root, output);
        setClientPerformance(root, output);
        setHostsInfo(root, output);
        setRgwCount(root, output);
        setIscsiDaemons(root, output);

        return output;
    }

    private static void setHealthStatus(JsonNode root, ObjectNode output) {
        output.put("health", root.path("health").path("status").asText("HEALTH_UNKNOWN"));
    }

    private static void setMonitorsInfo(JsonNode root, ObjectNode output) {
        int monitorsCount = root.path("mon_status").path("monmap").path("mons").size();
        output.put("monitors", monitorsCount);
        output.set("quorum", root.path("mon_status").path("quorum"));
    }

    private static void setScrubStatus(JsonNode root, ObjectNode output) {
        output.put("scrub_status", root.path("scrub_status").asText("Inactive"));
    }


    private static void setOsdStatus(JsonNode root, ObjectNode output) {
        ObjectNode osdStatus = output.putObject("osd_status");
        osdStatus.put("total", root.path("osd_map").path("osds").size());
        long osdUp = StreamSupport.stream(Spliterators.spliteratorUnknownSize(root.path("osd_map").path("osds").elements(), Spliterator.ORDERED), false)
                .filter(osd -> osd.path("up").asInt() == 1)
                .count();
        long osdIn = StreamSupport.stream(Spliterators.spliteratorUnknownSize(root.path("osd_map").path("osds").elements(), Spliterator.ORDERED), false)
                .filter(osd -> osd.path("in").asInt() == 1)
                .count();
        osdStatus.put("up", (int) osdUp);
        osdStatus.put("in", (int) osdIn);
    }

    private static void setPgInfo(JsonNode root, ObjectNode output) {
        ObjectNode pgInfo = output.putObject("pg_info");
        pgInfo.put("active_clean", root.path("pg_info").path("statuses").path("active+clean").asInt(0));
    }

    private static void setPoolsInfo(JsonNode root, ObjectNode output) {
        output.put("pools", root.path("pools").size());
    }

    private static void setDiskUsage(JsonNode root, ObjectNode output) {
        ObjectNode diskUsage = output.putObject("disk_usage");
        diskUsage.put("total", root.path("df").path("stats").path("total_bytes").asLong());
        diskUsage.put("used", root.path("df").path("stats").path("total_used_raw_bytes").asLong());
        diskUsage.put("available", root.path("df").path("stats").path("total_avail_bytes").asLong());
    }

    private static void setClientPerformance(JsonNode root, ObjectNode output) {
        ObjectNode clientPerf = output.putObject("client_perf");
        clientPerf.put("read_bytes_sec", root.path("client_perf").path("read_bytes_sec").asInt(0));
        clientPerf.put("write_bytes_sec", root.path("client_perf").path("write_bytes_sec").asInt(0));
        clientPerf.put("op_per_sec", root.path("client_perf").path("read_op_per_sec").asInt(0) +
                root.path("client_perf").path("write_op_per_sec").asInt(0));
    }

    private static void setHostsInfo(JsonNode root, ObjectNode output) {
        output.put("hosts", root.path("hosts").asInt(0));
    }

    private static void setRgwCount(JsonNode root, ObjectNode output) {
        output.put("rgw_count", root.path("rgw").asInt(0));
    }

    private static void setIscsiDaemons(JsonNode root, ObjectNode output) {
        ObjectNode iscsiDaemons = output.putObject("iscsi_daemons");
        iscsiDaemons.put("up", root.path("iscsi_daemons").path("up").asInt(0));
        iscsiDaemons.put("down", root.path("iscsi_daemons").path("down").asInt(0));
    }


}
