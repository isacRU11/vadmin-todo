package com.vadmin.todo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends AbstractErrorController {

    private static final String errorPath = "/error";

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(errorPath)
    public String handleErrors(HttpServletRequest request, Model model) {
        Map<String, Object> errorMap = getErrorAttributes(request, true);

        //エラーステータスが999の場合、403だと想定する
        if(String.valueOf(errorMap.get("status")).equals("999")){
            errorMap.clear();
            errorMap.put("status","403");
        }
        
        model.addAttribute("exception", errorMap);
        model.addAttribute("isDisplay", true);
        return errorPath;
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}