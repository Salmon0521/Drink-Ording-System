package service.orders;

import org.junit.Before;

public class OrderServiceImplTest {
    private OrdersService ordersService;

    @Before
    public void setUp() throws Exception {
        ordersService = new OrdersServiceImpl();
    }

}