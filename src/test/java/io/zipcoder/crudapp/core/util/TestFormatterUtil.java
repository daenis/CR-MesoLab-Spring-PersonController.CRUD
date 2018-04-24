package io.zipcoder.crudapp.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FormatterUtil.class)
public class TestFormatterUtil {

    private String entityAsString;

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private Object entity;

    @Test
    public void testWriteEntityAsJsonString() throws Exception {
        whenNew(ObjectMapper.class)
                .withNoArguments()
                .thenReturn(objectMapper);
        when(objectMapper.writeValueAsString(entity))
                .thenReturn(entityAsString);

        String returnedEntityAsString = FormatterUtil.writeEntityAsJsonString(entity);

        Assert.assertEquals("The expected String is not being returned",
                entityAsString, returnedEntityAsString);
    }

}
