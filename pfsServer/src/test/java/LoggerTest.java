import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/26.
 */
public class LoggerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void LogTest() {
        RuntimeException runtimeException = new RuntimeException("error");
        LOGGER.info("hello", runtimeException);
    }
}
