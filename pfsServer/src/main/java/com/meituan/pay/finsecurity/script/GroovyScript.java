package com.meituan.pay.finsecurity.script;

import javax.script.*;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public class GroovyScript {

    private static ScriptEngineManager manager;
    private static ScriptEngine engine;
    static {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("groovy");
    }

    public static String obtainKey(String eventData, String key) throws ScriptException {
        //2、预编译脚本
        CompiledScript dataScript = ((Compilable) engine).compile("import groovy.json.*;\n" +
                "jsonSlurper = new JsonSlurper()\n" +
                "eventData = jsonSlurper.parseText(eventData)");

        //3、绑定数据
        Bindings bindings = engine.createBindings();
        bindings.put("eventData", eventData);
        dataScript.eval(bindings);

        //4、执行脚本
        CompiledScript script = ((Compilable) engine).compile(key);
        return String.valueOf(script.eval(bindings));
    }
}
