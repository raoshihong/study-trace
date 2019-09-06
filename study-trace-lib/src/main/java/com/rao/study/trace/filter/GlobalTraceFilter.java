package com.rao.study.trace.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.rao.study.trace.utils.ThreadLocalUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Activate(group = {Constants.CONSUMER, Constants.PROVIDER} , order = -9999)
public class GlobalTraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //从附件中获取
        String eventId = invocation.getAttachment("eventId");
        if (StringUtils.isEmpty(eventId)) {
            RpcContext.getContext().setAttachment("eventId", String.valueOf(ThreadLocalUtils.get()));
        }else{
            Long event_id = ThreadLocalUtils.get();
            if (Objects.isNull(event_id)) {
                ThreadLocalUtils.set(Long.valueOf(eventId));
            }
        }

        return invoker.invoke(invocation);
    }

}
