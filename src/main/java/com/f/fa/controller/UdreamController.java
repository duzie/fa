package com.f.fa.controller;

import com.f.fa.util.Http;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/udream")
public class UdreamController {

    @Autowired
    private ObjectMapper om;

    @GetMapping
    public String show(Model model) throws IOException {
        JsonNode data = getData("1019793176204357633");
        JsonNode data1 = getData("1014202560627601408");
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> list1 = new ArrayList<>();
        toList(data, list);
        toList(data1, list1);
        model.addAttribute("list", list);
        model.addAttribute("list1", list1);
        return "udream";
    }

    private void toList(JsonNode data, List<Map<String, Object>> list) {
        data.forEach(n -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nickname", n.get("nickname").asText());
            map.put("waitingCount", n.get("waitingCount").asText());
            list.add(map);
        });
    }

    private JsonNode getData(String storeId) throws IOException {
        String ylData = Request.post("https://api.udream.com/queued/getCraftsmanQueuedStatusCache")
                .bodyForm(Form.form().add("storeId", storeId).build())
                .execute(Http.NO_TRUESTED_CLIENT)
                .returnContent()
                .asString();
        JsonNode jsonNode = om.readTree(ylData);
        return jsonNode.get("result");
    }
}
