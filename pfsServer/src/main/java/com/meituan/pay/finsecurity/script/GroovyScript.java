package com.meituan.pay.finsecurity.script;

import com.meituan.pay.finsecurity.constant.ScriptConstant;

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

    public static Object script(String jsonName, String jsonData, String expr) {
        try {
            //1、预编译脚本
            CompiledScript dataScript = ((Compilable) engine).compile(obtainScript(jsonName));

            //2、绑定数据
            Bindings bindings = engine.createBindings();
            bindings.put(jsonName, jsonData);
            dataScript.eval(bindings);

            //3、执行脚本
            CompiledScript script = ((Compilable) engine).compile(expr);
            return script.eval(bindings);
        } catch (Exception e) {
            throw new RuntimeException(String.format("excute script error. jsonData: %s, jsonName: %s, expr: %s", jsonData, jsonName, expr), e);
        }
    }

    private static String obtainScript(String jsonName) {
        if (ScriptConstant.CONTEXT_DATA.equals(jsonName)) {
            return ScriptConstant.CONTEXT_SCRIPT;
        } else {
            return String.format(ScriptConstant.SCRIPT, jsonName, jsonName);
        }
    }
}
