package goksel.elpeze.hw5.service;

import goksel.elpeze.hw5.model.Logger;
import goksel.elpeze.hw5.repository.LoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggerService {
    private final LoggerRepository loggerRepository;


    public List<Logger> findSalaryLogById(int id) {
        return loggerRepository.findAllById(id);
    }

    public List<Logger> findSalaryLogByDate(String transactionDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate logDate = LocalDate.parse(transactionDate, formatter);
        return loggerRepository.findAllByLogDate(logDate);
    }
}
