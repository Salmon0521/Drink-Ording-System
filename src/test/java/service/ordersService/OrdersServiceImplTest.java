package service.ordersService;

import org.junit.Before;

public class OrdersServiceImplTest {
    private OrdersService ordersService;

    @Before
    public void setUp() throws Exception {
        ordersService = new OrdersServiceImpl();
    }

}