import game.map.ConnectionCalculator;
import game.map.StarSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ConnectionCalculatorTest {
    @Test
    public void crossOrNot() {
        StarSystem sameStar = new StarSystem(4, 2);
        assertTrue(
                ConnectionCalculator.doIntersect(new StarSystem(1, 1),
                                                 new StarSystem(4, 3),
                                                 new StarSystem(3, 1),
                                                 new StarSystem(1, 4)
                                                ));
        assertFalse(
                ConnectionCalculator.doIntersect(new StarSystem(1, 1),
                                                 new StarSystem(4, 2),
                                                 new StarSystem(2, 2),
                                                 new StarSystem(4, 4)
                                                ), "is not true");
        assertFalse(
                ConnectionCalculator.doIntersect(new StarSystem(1, 1),
                                                 sameStar,
                                                 sameStar,
                                                 new StarSystem(4, 4)
                                                ), "is not true");
    }



}

