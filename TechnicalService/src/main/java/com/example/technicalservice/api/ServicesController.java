package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.service.responses.GetAllServices;
import com.example.technicalservice.dto.service.responses.GetServices;
import com.example.technicalservice.service.abstracts.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/service")
public class ServicesController {

    private final ServicesService service;

    @GetMapping(path = "/getall")
    DataResult<List<GetAllServices>> getAll(){
        return this.service.getAll();
    }
    @GetMapping(path = "/getById/{id}")
    DataResult<GetServices> getServices(@PathVariable(name = "id") long id){
        return this.service.getServices(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result delete(@PathVariable(name = "id") long id){
        return this.service.delete(id);
    }
}
