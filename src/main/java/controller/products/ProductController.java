package controller.products;

import bean.product.Product;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.product.ProductService;
import service.product.ProductServiceImpl;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService = new ProductServiceImpl();

    @GetMapping("/Chocolate")
    public ModelAndView showCocoView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
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
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }
        session.setAttribute("ProductName", productName);
        return new ModelAndView("customization/Customization");
    }

    @GetMapping("/Hand")
    public ModelAndView showHandView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> hand = productService.showProducts("Boutique hand coffee");
        ModelAndView mav = new ModelAndView("products/Hand");
        mav.addObject("json", new Gson().toJson(hand));
        return mav;
    }

    @PostMapping("/Hand")
    public ModelAndView handleHandPost(@RequestParam("productName") String productName,
                                       HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        session.setAttribute("ProductName", productName);
        return new ModelAndView("customization/Customization");
    }

    @GetMapping("/Latte")
    public ModelAndView showLatteView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> latte = productService.showProducts("Latte");
        ModelAndView mav = new ModelAndView("products/Latte");
        mav.addObject("json", new Gson().toJson(latte));
        return mav;
    }

    @PostMapping("/Latte")
    public ModelAndView handleLattePost(@RequestParam("productName") String productName,
                                        HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        session.setAttribute("ProductName", productName);
        return new ModelAndView("customization/Customization");
    }

    @GetMapping("/Tea")
    public ModelAndView showTeaView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> tea = productService.showProducts("Tea");
        ModelAndView mav = new ModelAndView("products/Tea");
        mav.addObject("json", new Gson().toJson(tea));
        return mav;
    }

    @PostMapping("/Tea")
    public ModelAndView handleTeaPost(@RequestParam("productName") String productName,
                                       HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        session.setAttribute("ProductName", productName);
        return new ModelAndView("customization/Customization");
    }

    @GetMapping("/Milk")
    public ModelAndView showMilkView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));

        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> milk = productService.showProducts("Milk");
        ModelAndView mav = new ModelAndView("products/Milk");
        mav.addObject("json", new Gson().toJson(milk));
        return mav;
    }

    @PostMapping("/Milk")
    public ModelAndView handleMilkPost(@RequestParam("productName") String productName,
                                        HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        session.setAttribute("ProductName", productName);
        return new ModelAndView("customization/Customization");
    }

    @GetMapping("/Coffee")
    public ModelAndView showRoastedView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> coffee = productService.showProducts("Coffee");
        ModelAndView mav = new ModelAndView("products/Coffee");
        mav.addObject("json", new Gson().toJson(coffee));
        return mav;
    }

    @PostMapping("/Coffee")
    public ModelAndView handleRoastedPost(@RequestParam("productName") String productName,
                                          HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        session.setAttribute("ProductName", productName);
        return new ModelAndView("customization/Customization");
    }
}
