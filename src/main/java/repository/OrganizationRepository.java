package repository;

public interface OrganizationRepository {
  void importFromCsv(final String filename);

  void exportToJson(final String filename);

  void sort();
}
