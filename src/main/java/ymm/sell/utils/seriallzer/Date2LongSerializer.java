package ymm.sell.utils.seriallzer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: ymm
 * @Date: 2018/8/5 19:01
 * @Description:
 */
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(final Date date, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime()/100);
    }
}
