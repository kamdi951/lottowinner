package pl.sda.input;

import org.junit.Before;
import org.mockito.Mock;
import pl.sda.model.GameType;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserInputTest {
    @Mock
    MyScanner scanner;

    @Mock
    GameTypeProvider gameTypeProvider;

    @Before
    public void setUp() throws Exception{
        openMocks(this);
    }



}
