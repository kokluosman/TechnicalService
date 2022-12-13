package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.service.responses.ServicesGetAllResponse;
import com.example.technicalservice.dto.service.responses.ServicesGetResponse;

import java.util.List;

public interface ServicesService {
    DataResult<List<ServicesGetAllResponse>> getAll();
    DataResult<ServicesGetResponse> getServices(long id);
    Result delete(long id);
}
