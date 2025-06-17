package org.app.lifemarchforecastingbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.historyDto.CreateHistoryDto;
import org.app.lifemarchforecastingbackend.dto.historyDto.HistoryDto;
import org.app.lifemarchforecastingbackend.dto.historyDto.HistoryMapper;
import org.app.lifemarchforecastingbackend.entities.HistoryEntity;
import org.app.lifemarchforecastingbackend.exceptions.NotFoundException;
import org.app.lifemarchforecastingbackend.exceptions.OperationErrorException;
import org.app.lifemarchforecastingbackend.repository.HistoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryMapper historyMapper;
    private final HistoryRepo historyRepo;

    public void createHistory(CreateHistoryDto historyDto) {
        log.debug("Starting to create history...");

        try {
            log.info("Create {}", historyDto.name());
            HistoryEntity history = historyMapper.toCreateEntity(historyDto);
            historyRepo.save(history);
            log.info("History with the product {} has been successfully created", historyDto.name());
        } catch (OperationErrorException e) {
        log.error("Failed to create history", e);
        throw e;
        }
    }

    public List<HistoryDto> getAllProductsFromHistory() {
        try {
            return historyRepo
                    .findAll()
                    .stream()
                    .map(historyMapper::toDto)
                    .toList();
        } catch (OperationErrorException e) {
            log.error("Failed to get products from history", e);
            throw e;
        }
    }

    public List<HistoryDto> getByName(String name) {
        try {
            return historyRepo
                    .findAllByName(name)
                    .stream()
                    .map(historyMapper::toDto)
                    .toList();
        } catch (OperationErrorException e) {
            log.error("Failed to get products from history with name {}", name, e);
            throw e;
        }
    }

    public void deleteProductFromHistory(Long id) {
        try {
            log.debug("Starting to delete product from history...");

            log.info("Finding product in history");
            if (historyRepo.findById(id).isEmpty())
                throw new NotFoundException("Product was not found in history");

            historyRepo.deleteById(id);
            log.info("Product was deleted from history");
        } catch (OperationErrorException e) {
            log.error("Failed to delete products from history", e);
            throw e;
        }
    }

}
