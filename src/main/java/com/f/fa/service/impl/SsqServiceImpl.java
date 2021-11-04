package com.f.fa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.fa.mapper.SsqMapper;
import com.f.fa.pojo.Ssq;
import com.f.fa.service.SsqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SsqServiceImpl extends ServiceImpl<SsqMapper, Ssq> implements SsqService {

    private static final String SSQ_URL = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=&issueEnd=&dayStart=%s&dayEnd=%s";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void init500() throws IOException {
        List<String> linkList = get500Link();
        for (String s : linkList) {
            Ssq ssq = get500Detail(s);
            Long count = this.lambdaQuery()
                    .eq(Ssq::getCode, ssq.getCode())
                    .count();
            if (count == 0) {
                this.save(ssq);
            }
        }
    }

//    public static void main(String[] args) throws IOException {
//
//        Ssq detail = new SsqServiceImpl().get500Detail(null);
//        log.debug(detail.toString());
//    }

    public Ssq get500Detail(String link) throws IOException {
//        link = "https://kaijiang.500.com/shtml/ssq/12002.shtml";
        log.debug(link);
        Ssq ssq = new Ssq();
        if (link.indexOf("17001")>0) {
            ssq.setCode("2017001");
            return ssq;
        }
        String content = Request.get(link)
                .execute()
                .returnContent()
                .asString(Charset.forName("gb2312"));

        Pattern pattern = Pattern.compile("class=\"ball_red\">(.*?)</li>");
        Matcher matcher = pattern.matcher(content);
        List<String> reds = new ArrayList<>();
        while (matcher.find()) {
            reds.add(matcher.group(1));
        }
        ssq.setRed1(Integer.parseInt(reds.get(0)));
        ssq.setRed2(Integer.parseInt(reds.get(1)));
        ssq.setRed3(Integer.parseInt(reds.get(2)));
        ssq.setRed4(Integer.parseInt(reds.get(3)));
        ssq.setRed5(Integer.parseInt(reds.get(4)));
        ssq.setRed6(Integer.parseInt(reds.get(5)));
        pattern = Pattern.compile("class=\"ball_blue\">(.*?)</li>");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            ssq.setBlue(Integer.parseInt(matcher.group(1)));
        }
        pattern = Pattern.compile("<font class=\"cfont2\"><strong>(.*?)</strong></font>");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            ssq.setCode("20" + matcher.group(1));
        }
        pattern = Pattern.compile("兑奖截止日期：(.*?)</span></td>");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            ssq.setDate(matcher.group(1));
        }
        return ssq;
    }

    private List<String> get500Link() throws IOException {
        String content = Request.get("https://kaijiang.500.com/shtml/ssq/03001.shtml")
                .execute()
                .returnContent()
                .asString();
        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                String link = matcher.group(1);
                if (link.startsWith("https://kaijiang.500.com/shtml/ssq/")) {
                    list.add(link);
                }
            }
        }
        return list;
    }

    @Override
    public void init() throws IOException {
        Date date = new Date();
        do {
            initByMonth(date);
            date = DateUtils.addMonths(date, -1);
        } while (!DateFormatUtils.format(date, "yyyy").equals("2012"));

    }

    private void initByMonth(Date date) throws IOException {
        Date start = DateUtils.truncate(date, Calendar.MONTH);
        Date end = DateUtils.ceiling(date, Calendar.MONTH);
        String s = DateFormatUtils.format(start, "yyyy-MM-dd");
        String e = DateFormatUtils.format(end, "yyyy-MM-dd");
        String url = String.format(SSQ_URL, s, e);
        log.debug(url);
        SsqRes ssqRes = objectMapper.readValue(new URL(url), SsqRes.class);
        if (ssqRes.getResult().size() == 0) {
            return;
        }
        ArrayList<Ssq> ssqList = new ArrayList<>();
        ssqRes.getResult()
                .forEach(sr -> {
                    Ssq ssq = new Ssq();
                    ssq.setCode(sr.getCode());
                    ssq.setDate(sr.getDate());
                    String[] reds = sr.getRed().split(",");
                    ssq.setRed1(Integer.parseInt(reds[0]));
                    ssq.setRed2(Integer.parseInt(reds[1]));
                    ssq.setRed3(Integer.parseInt(reds[2]));
                    ssq.setRed4(Integer.parseInt(reds[3]));
                    ssq.setRed5(Integer.parseInt(reds[4]));
                    ssq.setRed6(Integer.parseInt(reds[5]));
                    ssq.setBlue(Integer.parseInt(sr.getBlue()));
                    ssqList.add(ssq);
                });
        List<String> codeList = ssqList.stream().map(Ssq::getCode).collect(Collectors.toList());
        this.remove(Wrappers.<Ssq>lambdaQuery().in(Ssq::getCode, codeList));
        this.saveBatch(ssqList);
    }


}

@Data
class SsqRes {
    private List<SsqDetail> result;

}

@Data
class SsqDetail {
    private String code;
    private String date;
    private String red;
    private String blue;
}
