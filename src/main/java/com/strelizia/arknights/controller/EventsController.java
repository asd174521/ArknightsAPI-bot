package com.strelizia.arknights.controller;

import com.strelizia.arknights.dao.AdminUserMapper;
import com.strelizia.arknights.dao.ModelCountMapper;
import com.strelizia.arknights.model.*;
import com.strelizia.arknights.service.*;
import com.strelizia.arknights.util.AdminUtil;
import com.strelizia.arknights.util.ClassificationUtil;
import com.strelizia.arknights.util.SendMsgUtil;
import com.strelizia.arknights.util.ServerSystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangzy
 * @Date 2020/11/20 17:02
 * 通用的群聊回复接口
 **/
@RequestMapping("events")
@RestController
@Slf4j
public class EventsController {

    @Value("${userConfig.loginQq}")
    private Long loginQq;

    @Autowired
    private AgentService agentService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private TagsfFoundService tagsfFoundService;

    @Autowired
    private SendMsgUtil sendMsgUtil;

    @Autowired
    private SeTuService seTuService;

    @Autowired
    private BiliListeningService biliListeningService;

    @Autowired
    private EnemyService enemyService;

    @Autowired
    private ExecuteSqlService executeSqlService;

    @Autowired
    private OperatorInfoService operatorInfoService;

    @Autowired
    private BuildingSkillService buildingSkillService;

    @Autowired
    private SkillDescService skillDescService;

    @Autowired
    private SkinInfoService skinInfoService;

    @Autowired
    private ModelCountMapper modelCountMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private UpdateDataService updateDataService;

    @Autowired
    private PetPetService petPetService;

    /**
     * 事件消息处理
     * @param message
     * @return
     */
    @PostMapping("group")
    public String receive(
            @RequestBody EventsMessage message
    ) {
        Long qq = message.getQq();
        Long groupId = message.getGroupId();
        log.info("接受到事件消息:{}", message.getContent());
        String type = message.getType();
        JSONObject eventData = new JSONObject(message.getEventData());
        String result;
        switch (type){
            case "ON_EVENT_GROUP_JOIN":
                //入群事件
                result = "";
                petPetService.PetPet(groupId, qq, "欢迎" + eventData.getString("UserName") + "入群");
                break;
            default:
                result = "";
        }
        return result;
    }
}
