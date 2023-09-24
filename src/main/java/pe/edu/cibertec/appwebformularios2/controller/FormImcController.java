package pe.edu.cibertec.appwebformularios2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.cibertec.appwebformularios2.model.ImcModel;

@Controller
public class FormImcController {

    @GetMapping("/calcularimc")
    public String index(Model model){
        model.addAttribute("imcmodel",new ImcModel());
        model.addAttribute("resultado",false);
        return "formImc";
    }

    @PostMapping("/calcularimc")
    public String calcularimc(@ModelAttribute("imcmodel") ImcModel imcmode ,Model model  ){
        Double tallam=imcmode.getTalla() /100;
    Double valorimc =imcmode.getPeso() /(tallam*tallam);
    String condition="";
    String coloralert="alert-danger";
    if(valorimc<=18.5){
        condition="por debajo del peso";
        coloralert="alert-dark";
    } else if (valorimc<=25) {
        condition="con peso normal";
        coloralert="alert-primary";
    } else if (valorimc<=30) {
        condition="con sobrepeso";
        coloralert="alert-warning";
    } else if (valorimc<=35) {
        condition="con obesidad leve";

    } else if (valorimc<=39) {
        condition="con obesidad media";
    } else {
        condition="obesidad morbida" ;
    }
    model.addAttribute("verresultado",true);
    model.addAttribute("resultado","su valor de imc :"+String.format("%.2f",valorimc)+" usted e encuentra: "+condition);
    model.addAttribute("coloralert",coloralert);
        return "formImc";
    }

}
