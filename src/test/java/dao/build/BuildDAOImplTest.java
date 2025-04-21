package dao.build;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.DrinkShopApplication;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DrinkShopApplication.class)
public class BuildDAOImplTest {

    @Autowired
    private BuildDAO buildDAO;

    @BeforeEach
    public void setUp() throws Exception {
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrderID () {
        List<Integer> orderID = buildDAO.getOrderID(1, 1);
        assertEquals(Integer.valueOf(1), orderID.get(0));
    }
}