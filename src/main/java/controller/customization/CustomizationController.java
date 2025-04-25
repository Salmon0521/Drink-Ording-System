package controller.customization;

import bean.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.cart.CartService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CustomizationController {

    @Autowired
    private final CartService cartService;

    public CustomizationController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/Customization")
    public ModelAndView showCustomizationView(HttpSession session) {
        String productName = (String) session.getAttribute("ProductName");
        String account = (String) session.getAttribute("account");

        if (productName == null || account == null) {
            return new ModelAndView("redirect:/Login");
        }

        ModelAndView mav = new ModelAndView("customization/Customization");
        mav.addObject("productName", productName);
        return mav;
    }

    @PostMapping("/Customization")
    public ModelAndView addCustomizedProduct(@RequestParam("productName") String productName,
                                                       @RequestParam("size") String size,
                                                       @RequestParam("sugar") String sugar,
                                                       @RequestParam("ice") String ice,
                                                       @RequestParam("quantity") String quantity,
                                                       HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        String phone =  String.valueOf(session.getAttribute("phone"));


        if (productName == null || account == null) {
            return new ModelAndView("redirect:/Login");
        }

        Product product = new Product();
        product.setName(productName);

        if (size.equals("中杯")) {
            product.setSize("M");
        } else if (size.equals("大杯")) {
            product.setSize("L");
        }

        product.setSugar(sugar);
        product.setIce(ice);
        product.setQuantity(Integer.parseInt(quantity));

        cartService.addProduct(account, phone, product);

        return new ModelAndView("redirect:/CustomerMenu");
    }
}
