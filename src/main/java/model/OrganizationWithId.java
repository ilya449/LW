package model;

import lombok.Getter;
import lombok.ToString;

@Getter
public class OrganizationWithId extends Organization {
  @ToString.Exclude private static int idCounter = 1;

  @Override
  public String toString() {
    return super.toString().replaceFirst("\\(", "\\(id=" + this.id + ", ");
  }

  public OrganizationWithId(final String properties) {
    super(properties);
    this.id = (long) idCounter++;
  }

  private final Long id;

  @Override
  public String toJson() {
    return super.toJson();
  }
}
