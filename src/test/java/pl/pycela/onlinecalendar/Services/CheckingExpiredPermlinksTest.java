package pl.pycela.onlinecalendar.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pycela.onlinecalendar.Models.Permalink;
import pl.pycela.onlinecalendar.Repositories.EventRepository;
import pl.pycela.onlinecalendar.Repositories.PermalinkRepository;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CheckingExpiredPermlinksTest {

    @Mock
    EventRepository eventRepository;
    @Mock
    PermalinkRepository permalinkRepository;
    @InjectMocks
    private CheckingExpiredPermalinks checkingExpiredPermlinks;
    @Mock
    private Permalink permalink;

    @Test
    public void should_destroy_when_expiredDate() {
        LocalDateTime dateTime = LocalDateTime.now().minusDays(3);
        Mockito.when(permalink.getExpiryDateTime()).thenReturn(dateTime);
        boolean result = checkingExpiredPermlinks.isPermalinkToDestroy(permalink);
        assertTrue(result);
    }

    @Test
    public void should_not_destroy_when_expiredDate() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(4);
        Mockito.when(permalink.getExpiryDateTime()).thenReturn(dateTime);
        boolean result = checkingExpiredPermlinks.isPermalinkToDestroy(permalink);
        assertFalse(result);
    }


}