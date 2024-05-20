package club.c1sec.c1ctfplatform.services;

import club.c1sec.c1ctfplatform.dao.ConfigDao;
import club.c1sec.c1ctfplatform.po.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
@DependsOn({"configDao"})
public class ConfigService {
    @Autowired
    ConfigDao configDao;

    private String dynamicScoreBaseKey = "dynamic_score_base";
    private int dynamicScoreBase;

    private String dynamicScoreMinKey = "dynamic_score_min";
    private int dynamicScoreMin;

    private String matchOpenTimeKey = "match_open_time";
    private Instant matchOpenTime;

    private String matchEndTimeKey = "match_end_time";
    private Instant matchEndTime;

    private String registerOpenKey = "register_open";
    private boolean registerOpen;

    private String containerCountKey = "container_count";
    private int containerCount;

    private String containerFlagFormatKey = "container_flag_format";
    private String containerFlagFormat;

    @PostConstruct
    public void initConfigService() {
        this.refreshConfig();
    }

    public int getDynamicScoreBase() {
        return this.dynamicScoreBase;
    }

    public int getDynamicScoreMin() {
        return this.dynamicScoreMin;
    }

    public Instant getMatchOpenTime() {
        return this.matchOpenTime;
    }

    public Instant getMatchEndTime() {
        return this.matchEndTime;
    }

    public boolean getRegisterOpen() {
        return this.registerOpen;
    }

    public int getContainerCount() {
        return this.containerCount;
    }

    public String getContainerFlagFormat() {
        return this.containerFlagFormat;
    }


    public void setDynamicScoreBase(int value) {
        Config config = new Config();
        config.setKey(dynamicScoreBaseKey);
        config.setValue(Integer.toString(value));
        configDao.save(config);
    }

    public void setDynamicScoreMin(int value) {
        Config config = new Config();
        config.setKey(dynamicScoreMinKey);
        config.setValue(Integer.toString(value));
        configDao.save(config);
    }

    public void setMatchOpenTime(Instant value) {
        Config config = new Config();
        config.setKey(matchOpenTimeKey);
        config.setValue(value.toString());
        configDao.save(config);
    }

    public void setMatchEndTime(Instant value) {
        Config config = new Config();
        config.setKey(matchEndTimeKey);
        config.setValue(value.toString());
        configDao.save(config);
    }

    public void setContainerCount(int value) {
        Config config = new Config();
        config.setKey(containerCountKey);
        config.setValue(Integer.toString(value));
        configDao.save(config);
    }

    public void setContainerFlagFormat(String value) {
        Config config = new Config();
        config.setKey(containerFlagFormatKey);
        config.setValue(value);
        configDao.save(config);
    }

    public void setRegisterOpen(boolean value) {
        Config config = new Config();
        config.setKey(registerOpenKey);
        config.setValue(Boolean.toString(value));
        configDao.save(config);
    }

    public void refreshDynamicScoreBase() {
        try {
            Config config = configDao.findConfigByKey(dynamicScoreBaseKey);
            this.dynamicScoreBase = Integer.parseInt(config.getValue());
        } catch (Exception e) {
            this.dynamicScoreBase = 1000;
        }
    }

    public void refreshDynamicScoreMin() {
        try {
            Config config = configDao.findConfigByKey(dynamicScoreMinKey);
            this.dynamicScoreMin = Integer.parseInt(config.getValue());
        } catch (Exception e) {
            this.dynamicScoreMin = 10;
        }
    }

    public void refreshMatchOpenTime() {
        try {
            Config config = configDao.findConfigByKey(matchOpenTimeKey);
            this.matchOpenTime = Instant.parse(config.getValue());
        } catch (Exception e) {
            this.matchOpenTime = Instant.parse("2019-10-27T07:44:00Z");
        }
    }

    public void refreshMatchEndTime() {
        try {
            Config config = configDao.findConfigByKey(matchEndTimeKey);
            this.matchEndTime = Instant.parse(config.getValue());
        } catch (Exception e) {
            this.matchEndTime = Instant.parse("2019-11-27T07:45:00Z");
        }
    }


    public void refreshRegisterOpen() {
        try {
            Config config = configDao.findConfigByKey(registerOpenKey);
            this.registerOpen = Boolean.parseBoolean(config.getValue());
        } catch (Exception e) {
            this.registerOpen = true;
        }
    }

    public void refreshContainerCount() {
        try {
            Config config = configDao.findConfigByKey(registerOpenKey);
            this.registerOpen = Boolean.parseBoolean(config.getValue());
        } catch (Exception e) {
            this.registerOpen = true;
        }
    }

    public void refreshContainerFlagFormat() {
        try {
            Config config = configDao.findConfigByKey(registerOpenKey);
            this.registerOpen = Boolean.parseBoolean(config.getValue());
        } catch (Exception e) {
            // todo: 默认的flag格式
            this.containerFlagFormat = "C1CTF{}";
        }
    }

    public void refreshConfig() {
        this.refreshDynamicScoreBase();
        this.refreshDynamicScoreMin();
        this.refreshMatchOpenTime();
        this.refreshMatchEndTime();
        this.refreshRegisterOpen();
        this.refreshContainerCount();
        this.refreshContainerFlagFormat();

    }
}