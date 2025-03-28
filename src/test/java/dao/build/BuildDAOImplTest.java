package dao.build;

import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BuildDAOImplTest {
    private BuildDAO buildDAO;

    @Before
    public void setUp() throws Exception {
        buildDAO = new BuildDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrderID () {
        List<Integer> orderID = buildDAO.getOrderID(1, 1);
        assertEquals(Integer.valueOf(1), orderID.get(0));
    }
}