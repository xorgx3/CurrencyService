package pl.luks.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.luks.exception.CurrencyNotFoundException;
import pl.luks.exception.NotAllParameterException;
import pl.luks.service.CurrencyService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BigDecimal calculateCurrency(@RequestParam String amount, @RequestParam String amountCurrency, @RequestParam String targetCurrency) {
        if (!parametersNotEmpty(amount, amountCurrency, targetCurrency)) {
            throw new NotAllParameterException();
        } else {
            return currencyService.calculateCurrency(new BigDecimal(amount), amountCurrency, targetCurrency);
        }
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public String typeMismatchExpcetionHandler(Exception exception, HttpServletRequest request) {
        return "You have to type proper types of arguments!";
    }

    @ExceptionHandler(NotAllParameterException.class)
    @ResponseBody
    public String notAllParametersExpcetionHandler(Exception exception, HttpServletRequest request) {
        return "You have to type all parameters!";
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public String numberFormatExpcetionHandler(Exception exception, HttpServletRequest request) {
        return "You have to type number as first parameter!";
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseBody
    public String currencyNotFoundExpcetionHandler(Exception exception, HttpServletRequest request) {
        return "Given currency does not exist in NBP!";
    }

    private boolean parametersNotEmpty(String amount, String ammountCurrency, String targetCurrency) {
        return StringUtils.isNotEmpty(amount) && StringUtils.isNotEmpty(ammountCurrency) && StringUtils.isNotEmpty(targetCurrency);
    }

}
