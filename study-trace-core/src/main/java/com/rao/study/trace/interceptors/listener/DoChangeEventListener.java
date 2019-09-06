package com.rao.study.trace.interceptors.listener;

import java.util.List;

public class DoChangeEventListener implements DoInsertEventListener, DoDeleteEventListener, DoUpdateEventListener {

	@Override
	public void onPostDelete(List<Object> changeTable) {
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
	public void onPostInsert(List<Object> changeTable) {
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
	public void onPostUpdate(List<Object> changeTable) {
//		for (ChangeData changeData : changeTable) {
//			Long entityId = changeData.getEntityId();
//			List<ChangeObject> afterColumnList = changeData.getAfterColumnList();
//			List<ChangeObject> beforeColumnList = changeData.getBeforeColumnList();
//
//			List<PropertyHistory> list = new ArrayList<>();
//			for (ChangeObject beforeChangeObject : beforeColumnList) {
//				PropertyHistory propertyHistory = new PropertyHistory();
//				String property = beforeChangeObject.getName();
//				String oldValue = getExactValue(beforeChangeObject.getValue());
//				propertyHistory.setProperty(property);
//				propertyHistory.setOldVal(oldValue);
//
//				String newValue = StringUtils.EMPTY;
//				for (ChangeObject afterChangeObject : afterColumnList) {
//					if (StringUtils.equals(property, afterChangeObject.getName())) {
//						newValue = getExactValue(afterChangeObject.getValue());
//						break;
//					}
//				}
//
//				if (oldValue != null && !oldValue.equals(newValue)) {
//					propertyHistory.setNewVal(newValue);
//					list.add(propertyHistory);
//				}
//			}
//
//			if (CollectionUtils.isEmpty(list)) {
//				return;
//			}
//
//			HistoryEntry historyEntry = new HistoryEntry(URLUtil.getURL(), entityId, RandomCodeUtil.getRandomCode(),
//					changeData.getTableName(), SamsConstants.OPER_UPDATE, Tools.obj2Json(list),
//					Tools.obj2Json(changeData.getCurrentEntityMap()), UserUtil.getUserID(), IpUtil.getIp(),
//					DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
//			mongoOperations.insert(historyEntry);
//		}
	}

//	private String getExactValue(Object value) {
//		if (value == null)
//			return StringUtils.EMPTY;
//		if (value instanceof Boolean) {
//			boolean flag = Boolean.valueOf(value.toString()).booleanValue();
//			return flag ? "1" : "0";
//		}
//		if (value instanceof Number) {
//			return BigDecimal.valueOf((Double.parseDouble(value.toString()))).stripTrailingZeros().toPlainString();
//		}
//		return value.toString();
//	}
}
