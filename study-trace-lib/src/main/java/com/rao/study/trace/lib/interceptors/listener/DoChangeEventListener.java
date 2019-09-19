package com.rao.study.trace.lib.interceptors.listener;

import com.alibaba.fastjson.JSON;
import com.rao.study.trace.dto.EventContentDto;
import com.rao.study.trace.lib.AbstractSpan;
import com.rao.study.trace.lib.EventData;
import com.rao.study.trace.lib.interceptors.enums.DBActionTypeEnum;
import com.rao.study.trace.lib.interceptors.mybatis.ChangeEntity;
import com.rao.study.trace.lib.utils.ContextManager;
import com.rao.study.trace.lib.utils.TableConfig;
import com.rao.study.trace.lib.utils.TablesConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("doChangeEventListener")
public class DoChangeEventListener implements DoInsertEventListener, DoDeleteEventListener, DoUpdateEventListener {

	@Autowired
	private EventData eventData;

	@Override
	public void onPostDelete( List<ChangeEntity> changeTable) {
		for (ChangeEntity changeEntity : changeTable) {
			storeChangeEntity(changeEntity,DBActionTypeEnum.DELETE);
		}
	}

	@Override
	public void onPostInsert(List<ChangeEntity> changeTable) {
		for (ChangeEntity changeEntity : changeTable) {
			storeChangeEntity(changeEntity,DBActionTypeEnum.INSERT);
		}
	}

	@Override
	public void onPostUpdate(List<ChangeEntity> changeTable) {
		for (ChangeEntity changeEntity : changeTable) {
			storeChangeEntity(changeEntity,DBActionTypeEnum.UPDATE);
		}
	}

	private void storeChangeEntity(ChangeEntity changeEntity, DBActionTypeEnum actionTypeEnum){

		try {
			TableConfig tableConfig = TablesConfigUtils.getTableConfigByTableName(changeEntity.getTableName());

			Long entityId = changeEntity.getEntityId();
			String tableName = changeEntity.getTableName();
			if (Objects.nonNull(tableConfig)) {

				AbstractSpan abstractSpan = ContextManager.get();
				if (Objects.nonNull(abstractSpan)) {
					EventContentDto eventContentDto = new EventContentDto();
					eventContentDto.setSpanId(abstractSpan.getSpanId());
					eventContentDto.setDataActionType(actionTypeEnum.getValue());
					eventContentDto.setTableNameDesc(tableConfig.getTableNameDesc());
					eventContentDto.setTableName(tableName);
					eventContentDto.setRecordId(entityId);
					eventContentDto.setBeforeBody(JSON.toJSONString(changeEntity.getBeforeColumnList()));
					eventContentDto.setAfterBody(JSON.toJSONString(changeEntity.getAfterColumnList()));
					eventData.buildEventContent(eventContentDto);
				}
			}
		}catch (Exception e){

		}
	}

}
