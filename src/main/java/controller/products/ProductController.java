package controller.products;

import bean.product.Product;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.product.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private final ProductService productService;

    private static final String ACCOUNT = "account";
    private static final String LOGIN_PAGE = "redirect:/Login";
    private static final String CUSTOMIZATION_PAGE = "customization/Customization";
    private static final String PRODUCT_NAME = "ProductName";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/Chocolate")
    public ModelAndView showCocoView(HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        List<Product> chocolate = productService.showProducts("Chocolate");
        ModelAndView mav = new ModelAndView("/products/Chocolate");

        String cocoJson = new Gson().toJson(chocolate);
        mav.addObject("json", cocoJson);

        return mav;
    }

    @PostMapping("/Chocolate")
    public ModelAndView handleCocoPost(@RequestParam("productName") String productName,
                                                                     HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }
        session.setAttribute(PRODUCT_NAME, productName);
        return new ModelAndView(CUSTOMIZATION_PAGE);
    }

    @GetMapping("/Hand")
    public ModelAndView showHandView(HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        List<Product> hand = productService.showProducts("Boutique hand coffee");
        ModelAndView mav = new ModelAndView("products/Hand");
        mav.addObject("json", new Gson().toJson(hand));
        return mav;
    }

    @PostMapping("/Hand")
    public ModelAndView handleHandPost(@RequestParam("productName") String productName,
                                       HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        session.setAttribute(PRODUCT_NAME, productName);
        return new ModelAndView(CUSTOMIZATION_PAGE);
    }

    @GetMapping("/Latte")
    public ModelAndView showLatteView(HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));

        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        List<Product> latte = productService.showProducts("Latte");
        ModelAndView mav = new ModelAndView("products/Latte");
        mav.addObject("json", new Gson().toJson(latte));
        return mav;
    }

    @PostMapping("/Latte")
    public ModelAndView handleLattePost(@RequestParam("productName") String productName,
                                        HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));

        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        session.setAttribute(PRODUCT_NAME, productName);
        return new ModelAndView(CUSTOMIZATION_PAGE);
    }

    @GetMapping("/Tea")
    public ModelAndView showTeaView(HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));

        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        List<Product> tea = productService.showProducts("Tea");
        ModelAndView mav = new ModelAndView("products/Tea");
        mav.addObject("json", new Gson().toJson(tea));
        return mav;
    }

    @PostMapping("/Tea")
    public ModelAndView handleTeaPost(@RequestParam("productName") String productName,
                                       HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));

        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        session.setAttribute(PRODUCT_NAME, productName);
        return new ModelAndView(CUSTOMIZATION_PAGE);
    }

    @GetMapping("/Milk")
    public ModelAndView showMilkView(HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));

        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        List<Product> milk = productService.showProducts("Milk");
        ModelAndView mav = new ModelAndView("products/Milk");
        mav.addObject("json", new Gson().toJson(milk));
        return mav;
    }

    @PostMapping("/Milk")
    public ModelAndView handleMilkPost(@RequestParam("productName") String productName,
                                        HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        session.setAttribute(PRODUCT_NAME, productName);
        return new ModelAndView(CUSTOMIZATION_PAGE);
    }

    @GetMapping("/Coffee")
    public ModelAndView showRoastedView(HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        List<Product> coffee = productService.showProducts("Coffee");
        ModelAndView mav = new ModelAndView("products/Coffee");
        mav.addObject("json", new Gson().toJson(coffee));
        return mav;
    }

    @PostMapping("/Coffee")
    public ModelAndView handleRoastedPost(@RequestParam("productName") String productName,
                                          HttpSession session) {
        String account = String.valueOf(session.getAttribute(ACCOUNT));
        if (account == null) {
            return new ModelAndView(LOGIN_PAGE);
        }

        session.setAttribute(PRODUCT_NAME, productName);
        return new ModelAndView(CUSTOMIZATION_PAGE);
    }
}
