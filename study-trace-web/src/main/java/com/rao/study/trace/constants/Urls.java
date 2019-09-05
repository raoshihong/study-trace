package com.rao.study.trace.constants;

public interface Urls {
    interface V1{
        String V1 = "/v1";

        interface Event{
            String EVENT = V1 + "/event";
            String LIST = EVENT + "/list";
            String SAVE = EVENT + "/save";
        }
    }
}
