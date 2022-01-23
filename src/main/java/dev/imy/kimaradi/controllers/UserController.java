package dev.imy.kimaradi.controllers;


import dev.imy.kimaradi.models.MissedCallResponse;
import dev.imy.kimaradi.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private CallService callService;
    @Autowired
    public UserController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/missed-calls/{phone}")
    public ResponseEntity<String> getMissedCalls(@PathVariable String phone){

        return ResponseEntity.ok(callService.getMissedCallResponse(phone));

    }

}
