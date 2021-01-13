package com.github.xiaoy.droolrule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoy.droolrule.entity.RuleConfigInfo;
import com.github.xiaoy.droolrule.entity.RuleConfigInfoDetail;
import com.github.xiaoy.droolrule.gen.param.DeveloperSettlementTemp;
import com.github.xiaoy.droolrule.mapper.RuleConfigInfoDetailMapper;
import com.github.xiaoy.droolrule.mapper.RuleConfigInfoMapper;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import com.github.xiaoy.droolrule.utils.SnowFlakeUtil;
import com.github.xiaoy.droolrule.vo.RuleInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 远程调用查询规则所需参数
 *
 * @author liuyongtao
 * @since 2021-1-12 19:39
 */
@Service
public class DeveloperSettlementRuleServiceImpl implements RuleInfoService<DeveloperSettlementTemp> {

    @Autowired
    private RuleConfigInfoMapper ruleConfigInfoMapper;
    @Autowired
    private RuleConfigInfoDetailMapper ruleConfigInfoDetailMapper;

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<RuleInfoVo> findRuleList() {

        LambdaQueryWrapper<RuleConfigInfo> wrapper = new LambdaQueryWrapper();
        wrapper.eq(RuleConfigInfo::getValidInd, true)
                .isNotNull(RuleConfigInfo::getGroupId)
                .eq(RuleConfigInfo::getIsDelete, false);
        List<RuleConfigInfo> ruleConfigInfos = ruleConfigInfoMapper.selectList(wrapper);

        List<RuleInfoVo> collect = ruleConfigInfos.stream().map(m -> {
            RuleInfoVo vo = new RuleInfoVo();
            vo.setId(m.getId());
            vo.setGroupId(m.getGroupId());
            vo.setRuleContent(m.getRuleContent());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<RuleInfoVo> findRuleListByGroupId(Long groupId) {
        LambdaQueryWrapper<RuleConfigInfo> wrapper = new LambdaQueryWrapper();
        wrapper.eq(RuleConfigInfo::getValidInd, true)
                .eq(RuleConfigInfo::getIsDelete, false)
                .eq(RuleConfigInfo::getGroupId, groupId);
        List<RuleConfigInfo> ruleConfigInfos = ruleConfigInfoMapper.selectList(wrapper);

        List<RuleInfoVo> collect = ruleConfigInfos.stream().map(m -> {
            RuleInfoVo vo = new RuleInfoVo();
            vo.setId(m.getId());
            vo.setGroupId(m.getGroupId());
            vo.setRuleContent(m.getRuleContent());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<DeveloperSettlementTemp> findGenerateRuleParam(Long bizId) {
        LambdaQueryWrapper<RuleConfigInfoDetail> wrapper = new LambdaQueryWrapper();
        wrapper.eq(RuleConfigInfoDetail::getValidInd, true)
                .eq(RuleConfigInfoDetail::getIsDelete, false)
                .eq(RuleConfigInfoDetail::getRuleConfigId, bizId);
        List<RuleConfigInfoDetail> infoDetailList = ruleConfigInfoDetailMapper.selectList(wrapper);
        // 转换对象
        List<DeveloperSettlementTemp> collect = infoDetailList.stream().map(m -> {
            DeveloperSettlementTemp temp = new DeveloperSettlementTemp();
            BeanUtils.copyProperties(m, temp);
            temp.setHouseTypeName(m.getHouseType());
            temp.setStartDate(format.format(m.getStartDate()));
            temp.setEndDate(format.format(m.getEndDate()));
            return temp;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void updateRule(RuleInfoVo vo) {
        RuleConfigInfo ruleConfigInfo = ruleConfigInfoMapper.selectById(vo.getId());
        if (ruleConfigInfo.getGroupId() == null) {
            vo.setGroupId(SnowFlakeUtil.getFlowIdInstance().nextId());
        } else {
            vo.setGroupId(ruleConfigInfo.getGroupId());
        }
        RuleConfigInfo info = new RuleConfigInfo();
        info.setId(vo.getId());
        info.setGroupId(vo.getGroupId());
        info.setRuleContent(vo.getRuleContent());
        ruleConfigInfoMapper.updateById(info);
    }
}
