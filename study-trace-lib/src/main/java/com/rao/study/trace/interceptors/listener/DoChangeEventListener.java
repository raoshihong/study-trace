package com.rao.study.trace.interceptors.listener;

import com.rao.study.trace.AbstractSpan;
import com.rao.study.trace.EventData;
import com.rao.study.trace.dto.EventContentDto;
import com.rao.study.trace.interceptors.mybatis.ChangeData;
import com.rao.study.trace.interceptors.mybatis.ChangeObject;
import com.rao.study.trace.interceptors.mybatis.PropertyHistory;
import com.rao.study.trace.utils.SpanContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("doChangeEventListener")
public class DoChangeEventListener implements DoInsertEventListener, DoDeleteEventListener, DoUpdateEventListener {

	@Autowired
	private EventData eventData;

	@Override
	public void onPostDelete(List<ChangeData> changeTable) {
//		for (ChangeData changeData : changeTable) {
//			Long entityId = changeData.getEntityId();
//			HistoryEntry historyEntry = new HistoryEntry(URLUtil.getURL(), entityId, RandomCodeUtil.getRandomCode(),
//					changeData.getTableName(), SamsConstants.OPER_DEL, StringUtils.EMPTY,
//					Tools.obj2Json(changeData.getCurrentEntityMap()), UserUtil.getUserID(), IpUtil.getIp(),
//					DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
//			mongoOperations.insert(historyEntry);
//		}
	}

	@Override
	public void onPostInsert(List<ChangeData> changeTable) {
//		for (ChangeData changeData : changeTable) {
//			Long entityId = changeData.getEntityId();
//			HistoryEntry historyEntry = new HistoryEntry(URLUtil.getURL(), entityId, RandomCodeUtil.getRandomCode(),
//					changeData.getTableName(), SamsConstants.OPER_ADD, StringUtils.EMPTY,
//					Tools.obj2Json(changeData.getCurrentEntityMap()), UserUtil.getUserID(), IpUtil.getIp(),
//					DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
//			mongoOperations.insert(historyEntry);
//		}
	}

	@Override
	public void onPostUpdate(List<ChangeData> changeTable) {
		for (ChangeData changeData : changeTable) {
			Long entityId = changeData.getEntityId();
			List<ChangeObject> afterColumnList = changeData.getAfterColumnList();
			List<ChangeObject> beforeColumnList = changeData.getBeforeColumnList();

			List<PropertyHistory> propertyHistories = new ArrayList<>();
			for (ChangeObject beforeChangeObject : beforeColumnList) {
				PropertyHistory propertyHistory = new PropertyHistory();
				String property = beforeChangeObject.getName();
				String oldValue = getExactValue(beforeChangeObject.getValue());
				propertyHistory.setProperty(property);
				propertyHistory.setOldVal(oldValue);

				String newValue = null;
				//遍历修改后的数据
				for (ChangeObject afterChangeObject : afterColumnList) {

					if (StringUtils.equals(property, afterChangeObject.getName())) {
						newValue = getExactValue(afterChangeObject.getValue());
						break;
					}
				}

				if (oldValue != null && !oldValue.equals(newValue)) {
					propertyHistory.setNewVal(newValue);
					propertyHistories.add(propertyHistory);
				}
			}

			if (CollectionUtils.isEmpty(propertyHistories)) {
				return;
			}

			save(propertyHistories);
		}
	}

	private String getExactValue(Object value) {
		if (value == null)
			return StringUtils.EMPTY;
		if (value instanceof Boolean) {
			boolean flag = Boolean.valueOf(value.toString()).booleanValue();
			return flag ? "1" : "0";
		}
		if (value instanceof Number) {
			return BigDecimal.valueOf((Double.parseDouble(value.toString()))).stripTrailingZeros().toPlainString();
		}
		return value.toString();
	}

	private void save(List<PropertyHistory> propertyHistories){
		AbstractSpan abstractSpan = SpanContextUtils.get();
		if (Objects.nonNull(abstractSpan) && Objects.nonNull(abstractSpan.getSpanId())) {
			Long eventId = abstractSpan.getEventId();
			//TODO 如果批量更新,比如是排序,则此时dataId就是当前数据的id,否则就是主表操作的那个数据的id
			if (Objects.nonNull(eventId)) {
				propertyHistories.stream().forEach(propertyHistory -> {
					EventContentDto eventContentDto = new EventContentDto();
					eventContentDto.setEventId(eventId);
					eventContentDto.setDataId(null);//TODO
					eventContentDto.setDataName("");
					eventContentDto.setKeyEn(propertyHistory.getProperty());
					eventContentDto.setKeyZh("");//TODO 中文名需要映射
					eventContentDto.setOlValue(propertyHistory.getOldVal());
					eventContentDto.setNeValue(propertyHistory.getNewVal());
					eventData.buildEventContent(eventContentDto);
				});
			}
		}
	}
}
