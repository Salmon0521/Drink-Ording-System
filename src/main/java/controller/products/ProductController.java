package controller.products;

import bean.product.Product;
import com.google.gson.Gson;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductController {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @GetMapping("/Coco")
    public ModelAndView showCocoView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> coco = productDAO.getChocolate();
        ModelAndView mav = new ModelAndView("/products/Coco");

        String cocoJson = new Gson().toJson(coco);
        mav.addObject("json", cocoJson);

        return mav;
    }

    @PostMapping("/Coco")
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

        List<Product> hand = productDAO.getBoutique();
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

        List<Product> latte = productDAO.getLatte();
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

        List<Product> tea = productDAO.getTea();
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

        List<Product> milk = productDAO.getMilk();
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

    @GetMapping("/Roasted")
    public ModelAndView showRoastedView(HttpSession session) {
        String account = String.valueOf(session.getAttribute("account"));
        if (account == null) {
            return new ModelAndView("redirect:/Login");
        }

        List<Product> roasted = productDAO.getCoffee();
        ModelAndView mav = new ModelAndView("products/Roasted");
        mav.addObject("json", new Gson().toJson(roasted));
        return mav;
    }

    @PostMapping("/Roasted")
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
