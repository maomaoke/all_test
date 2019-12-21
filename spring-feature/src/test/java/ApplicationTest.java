import com.google.common.collect.Maps;
import com.open.demo.spring.utils.DateTimeUtils;
import com.open.demo.spring.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-19-6:55 下午
 */
@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Test
    public void testStrToDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+00:00"));
        Date date = DateTimeUtils.strToDate("2019-12-19 00:00:00");
        log.info("{}", date.toString());
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        String json = JsonUtils.obj2Json(hashMap);
        log.info(json);
    }

}
