package com.spring.demo.boot;

import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.StandardSystemProperty.FILE_SEPARATOR;
import static com.google.common.base.StandardSystemProperty.USER_DIR;

/**
 * 嵌入式jetty启动类
 * 步骤:
 * 1.Create a Server instance.
 * 2.Add/Configure Connectors.
 * 3.Add/Configure Handlers and/or Contexts and/or Servlets.
 * 4.Start the Server.
 * 5.Wait on the server or do something else with your thread.
 */
public class JettyBoot {
    private static Logger logger = LoggerFactory.getLogger(JettyBoot.class);


    public static void main(String... anArgs) throws Exception {

        long begin = System.currentTimeMillis();

        Config config = initParam(anArgs);

        startJetty(config);

        logger.info("jetty关闭,running-time:{}ms", (System.currentTimeMillis() - begin));
    }


    /**
     * 初始化参数
     *
     * @param anArgs
     */
    private static Config initParam(String... anArgs) throws IOException {
        Config config = new Config();

        for (String arg : anArgs) {
            logger.info("修改jetty默认参数:{}", arg);
            if (StringUtils.isEmpty(arg) || !arg.contains("=")) {
                break;
            }

            String[] t = arg.trim().split("=");
            if ("port".equals(t[0])) {
                config.setPort(Integer.parseInt(t[1]));
            } else if ("contextPath".equals(t[0])) {
                config.setContextPath(t[1]);
            } else if ("tempDir".equals(t[0])) {
                config.setContextPath(t[1]);
            } else if ("logDir".equals(t[0])) {
                config.setContextPath(t[1]);
            }
        }

        logger.info(config.toString());

        String temp = "x.x";//占位
        Files.createParentDirs(new File(config.getLogDir() + "/" + temp));
        Files.createParentDirs(new File(config.getTempDir() + "/" + temp));

        return config;
    }


    private static void startJetty(Config config) throws Exception {
        logger.info("准备启动jetty");

        //1.Create a Server instance.
        Server server = new Server();
        server.setStopAtShutdown(true);

        //2.Add/Configure Connectors.
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(config.getPort());
        server.addConnector(connector);

        //3.Add/Configure Handlers and/or Contexts and/or Servlets.
        WebAppContext context = new WebAppContext();
        context.setContextPath(config.getContextPath());
        context.setWar(config.getWebDir());
        context.setTempDirectory(new File(config.getTempDir()));

        server.setHandler(context);

        //4.Start the Server.
        server.start();

        logger.info("jetty已启动");
        //5.Wait on the server or do something else with your thread.
        server.join();
    }

    private static class Config {

        private int port = 8080;
        private String contextPath = "/";
        private String tempDir = USER_DIR.value() + FILE_SEPARATOR.value() + "data/console/industryMa20Break/2018半导体及元器件";
        private String webDir = Config.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        private String logDir = USER_DIR.value() + FILE_SEPARATOR.value() + "log";

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }

        public String getTempDir() {
            return tempDir;
        }

        public void setTempDir(String tempDir) {
            this.tempDir = tempDir;
        }

        public String getWebDir() {
            return webDir;
        }

        public void setWebDir(String webDir) {
            this.webDir = webDir;
        }

        public String getLogDir() {
            return logDir;
        }

        public void setLogDir(String logDir) {
            this.logDir = logDir;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "port=" + port +
                    ", contextPath='" + contextPath + '\'' +
                    ", tempDir='" + tempDir + '\'' +
                    ", webDir='" + webDir + '\'' +
                    ", logDir='" + logDir + '\'' +
                    '}';
        }
    }

}
