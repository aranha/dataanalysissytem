package br.com.aranha.dataanalysissystem.service;

import org.springframework.stereotype.Service;

@Service
public interface RowDataService {
    void processNewRowData(String parsedLine);
}
