package com.strelizia.arknights.job;

import com.strelizia.arknights.dao.ActivityMapper;
import com.strelizia.arknights.dao.UserFoundMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wangzy
 * @Date 2020/12/8 15:53
 **/

@Component
@Slf4j
public class CleanDayCountJob {

    @Autowired
    private UserFoundMapper userFoundMapper;

    @Autowired
    private ActivityMapper activityMapper;

    //每天凌晨四点重置抽卡次数
    @Scheduled(cron = "${scheduled.cleanJob}")
    @Async
    public void cleanDayCountJob() {
        activityMapper.clearActivity();
        userFoundMapper.cleanTodayCount();
        log.info("{}每日涩图抽卡数结算成功", new Date());
    }
}
