package com.rao.study.trace.service.handler;

import com.alibaba.fastjson.JSON;
import com.welab.thor.bi.common.validate.Validator;
import com.welab.thor.bi.entity.LoanApplicationReport;
import com.welab.thor.bi.enums.LoanStateEnum;
import com.welab.thor.bi.event.LoanApplicationReportEvent;
import com.welab.thor.bi.exception.BusinessException;
import com.welab.thor.bi.service.LoanApplicationReportService;
import com.welab.thor.bi.utils.DateFormatUtils;
import com.welab.thor.bi.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 进件事件通知,进件统计计算
 */
@Component
@Slf4j
public class LoanApplicationReportEventHandler {

    @Autowired
    private LoanApplicationReportService loanApplicationReportService;

    @Resource(name = "loanApplicationReportEventValidator")
    private Validator<LoanApplicationReportEvent> loanApplicationReportEventValidator;

    /**
     * 处理消息
     *
     * @param loanApplicationReportEvent
     */
    @EventListener(classes = LoanApplicationReportEvent.class)
    public void applicationReportEventHandler(LoanApplicationReportEvent loanApplicationReportEvent) {//{"@type":"com.welab.thor.bi.event.LoanApplicationReportEvent","loanDate":null,"provinceCode":0,"cityCode":11,"districtCode":0,"amount":null,"name":"进件"}

        try {
            log.info("applicationReportEventHandler:{}", JSON.toJSONString(loanApplicationReportEvent));
            //验证消息数据格式是否正常
            loanApplicationReportEventValidator.validate(loanApplicationReportEvent);

            Integer appId = loanApplicationReportEvent.getAppId();
            String loanState = loanApplicationReportEvent.getLoanState();
            String reportDate = loanApplicationReportEvent.getReportDate();
            Integer provinceCode = loanApplicationReportEvent.getProvinceCode();
            Integer cityCode = loanApplicationReportEvent.getCityCode();
            Integer districtCode = loanApplicationReportEvent.getDistrictCode();
            BigDecimal amount = loanApplicationReportEvent.getAmount();


            //构建进件单报告信息
            LoanApplicationReport loanApplicationReport = new LoanApplicationReport();
            loanApplicationReport.setAppId(appId);
            loanApplicationReport.setReportDate(DateUtils.dateStringToDate(reportDate, DateFormatUtils.YEAR_MONTH_DAY_PATTERN));
            loanApplicationReport.setProvinceCode(provinceCode);
            loanApplicationReport.setCityCode(cityCode);
            loanApplicationReport.setDistrictCode(districtCode);
            Date date = DateUtils.localDateTimeToDateUseDefaultZone(LocalDateTime.now());
            loanApplicationReport.setUpdateTime(date);

            if(LoanStateEnum.APPLIED.getState().equals(loanState)){
                loanApplicationReport.setAppliedAmount(amount);
                loanApplicationReport.setAppliedTotal(1L);
            }else if(LoanStateEnum.DISBURSED.getState().equals(loanState)){
                loanApplicationReport.setDisbursedAmount(amount);
                loanApplicationReport.setDisbursedTotal(1L);
            }else{
                log.error("进件状态不对");
                throw new BusinessException("","loan.application.report.event.loanState.no.exist");
            }

            try {
                Integer result = loanApplicationReportService.loanApplicationReportInsertOrUpdate(loanApplicationReport, false);
                if(result<=0){
                    loanApplicationReport.setCreateTime(date);
                    loanApplicationReportService.loanApplicationReportInsertOrUpdate(loanApplicationReport, true);
                }
            } catch (DuplicateKeyException e) {
                //处理并发
                Integer result = loanApplicationReportService.loanApplicationReportInsertOrUpdate(loanApplicationReport, false);
            }

        }catch (Exception e){
            log.error("消息有问题:"+e.getMessage());
        }

    }
}
