package br.com.aranha.dataanalysissystem.service;

import br.com.aranha.dataanalysissystem.enums.DataType;
import org.springframework.stereotype.Service;

@Service
public class LineInputService {
    public void saveLine(String line) {
        String idDataType = line.substring(0, 3);
        DataType dataType = DataType.getByLineTypeId(idDataType);
        dataType.getRowDataService().processNewRowData(line);
    }
}
