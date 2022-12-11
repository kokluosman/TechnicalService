package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.service.responses.GetAllServices;
import com.example.technicalservice.dto.service.responses.GetServices;

import java.util.List;

public interface ServicesService {
    DataResult<List<GetAllServices>> getAll();
    DataResult<GetServices> getServices(long id);
    Result delete(long id);
}
