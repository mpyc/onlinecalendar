package pl.pycela.onlinecalendar.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GeneretingPermlinkTest {

    @Mock
    PermalinkRepository permalinkRepository;
    @InjectMocks
    private GeneretingPermlink generetingPermlink;

    @Test
    public void should_consist_24_alphanumeric() {
        assertTrue(generetingPermlink.alphanumericString().length() == 24);
    }

}