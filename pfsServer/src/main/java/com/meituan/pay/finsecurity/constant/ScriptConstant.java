package com.meituan.pay.finsecurity.constant;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/11/2.
 */
public class ScriptConstant {
    public static final String EVENT_DATA = "eventData";
    public static final String CONTEXT_DATA = "contextData";

    public static final String NULL = "null";

    public static final String CONTEXT_SCRIPT = "import groovy.json.*;\n " +
            "jsonSlurper = new JsonSlurper()\n " +
            "contextData = jsonSlurper.parseText(contextData)\n " +
            "if(contextData.eventData != null) eventData = jsonSlurper.parseText(contextData.eventData)\n" +
            "if(contextData.tradeData != null) tradeData = jsonSlurper.parseText(contextData.tradeData)";
}
