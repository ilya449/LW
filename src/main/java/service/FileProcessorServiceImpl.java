package service;

import repository.OrganizationRepository;
import repository.OrganizationRepositoryImpl;

import static config.Constants.DOT;
import static config.Constants.JSON;

public class FileProcessorServiceImpl implements FIleProcessorService {
  private final OrganizationRepository repository;

  public FileProcessorServiceImpl() {
    this.repository = new OrganizationRepositoryImpl();
  }

  @Override
  public void process(final String file) {
    repository.importFromCsv(file);
    System.out.println(repository.toString());
    repository.sort();
    System.out.println(repository.toString());
    final String jsonFileName = file.substring(0, file.lastIndexOf(DOT)) + JSON;
    repository.exportToJson(jsonFileName);
  }
}
