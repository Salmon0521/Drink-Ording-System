package dao.build;

import org.junit.Before;
import util.DatabaseUtil;

public class BuildDAOImplTest {
    private BuildDAO buildDAO;

    @Before
    public void setUp() throws Exception {
        buildDAO = new BuildDAOImpl();
        DatabaseUtil.initDatabase();
    }



}