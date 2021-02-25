package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import exception.BlankFieldException;
import exception.FieldParsingException;
import exception.MyCustomException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static config.Constants.ADDRESS;
import static config.Constants.CAN_NOT_PARSE_LINE;
import static config.Constants.COMMA;
import static config.Constants.COMMERCIALLY;
import static config.Constants.CONVERT_TO_JSON_ERROR_MESSAGE;
import static config.Constants.CREATION_DATE;
import static config.Constants.DIGITS_REGEX;
import static config.Constants.DOMAIN_REGEX;
import static config.Constants.EMPLOYEES_COUNT;
import static config.Constants.EMPTY_LINE;
import static config.Constants.MFO;
import static config.Constants.MFO_LENGTH;
import static config.Constants.NAME;
import static config.Constants.PROPERTIES_COUNT;
import static config.Constants.SITE;
import static config.Constants.SPACE;
import static config.Constants.WRONG_FILE_HEADER;

@Getter
@Log4j
@ToString
public abstract class Organization implements Comparable<Organization> {
  @ToString.Exclude private static String[] entityProperties;

  private String name;
  private String address;
  private Integer employeesCount;

  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate creationDate;

  private String mfo;
  private String site;
  private Boolean commercially;

  public Organization(final String properties) {
    if (entityProperties == null || entityProperties.length != PROPERTIES_COUNT) {
      throw new MyCustomException(WRONG_FILE_HEADER);
    }
    final String[] splitProps = properties.replaceAll(SPACE, EMPTY_LINE).split(COMMA);
    final List<String> propsList = Arrays.asList(entityProperties);
    if (splitProps.length == PROPERTIES_COUNT) {
      this.setName(splitProps[propsList.indexOf(NAME)]);
      this.setAddress(splitProps[propsList.indexOf(ADDRESS)]);
      this.setEmployeesCount(splitProps[propsList.indexOf(EMPLOYEES_COUNT)]);
      this.setCreationDate(splitProps[propsList.indexOf(CREATION_DATE)]);
      this.setMfo(splitProps[propsList.indexOf(MFO)]);
      this.setSite(splitProps[propsList.indexOf(SITE)]);
      this.setCommercially(splitProps[propsList.indexOf(COMMERCIALLY)]);
    } else {
      throw new MyCustomException(CAN_NOT_PARSE_LINE + properties);
    }
  }

  public static void resolveProperties(final String props) {
    entityProperties = props.replaceAll(SPACE, EMPTY_LINE).split(COMMA);
  }

  @Override
  public int compareTo(final Organization o) {
    final int i = this.name.compareToIgnoreCase(o.getName());
    return i != 0 ? i : this.getEmployeesCount().compareTo(o.getEmployeesCount());
  }

  public String toJson() {
    final ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(this);
    } catch (final JsonProcessingException e) {
      log.error(CONVERT_TO_JSON_ERROR_MESSAGE + this.toString());
      log.error(e.getMessage());
    }
    return EMPTY_LINE;
  }

  public void setName(final String name) {
    if (StringUtils.isBlank(name)) {
      throw new BlankFieldException(NAME);
    }
    this.name = name;
  }

  public void setAddress(final String address) {
    if (StringUtils.isBlank(address)) {
      throw new BlankFieldException(ADDRESS);
    }
    this.address = address;
  }

  public void setEmployeesCount(final String employeesCount) {
    try {
      this.employeesCount = Integer.parseInt(employeesCount);
    } catch (final NumberFormatException e) {
      throw new FieldParsingException(EMPLOYEES_COUNT);
    }
  }

  public void setCreationDate(final String creationDate) {
    try {
      this.creationDate = LocalDate.parse(creationDate);
    } catch (final DateTimeParseException e) {
      throw new FieldParsingException(CREATION_DATE);
    }
  }

  public void setMfo(final String mfo) {
    if (StringUtils.isBlank(mfo)) {
      throw new BlankFieldException(MFO);
    }
    if (mfo.length() != MFO_LENGTH || !mfo.matches(DIGITS_REGEX)) this.mfo = mfo;
  }

  public void setSite(final String site) {
    if (StringUtils.isBlank(site)) {
      throw new BlankFieldException(SITE);
    }
    if (!site.matches(DOMAIN_REGEX)) {
      throw new FieldParsingException(site);
    }
    this.site = site;
  }

  public void setCommercially(final String commercially) {
    if (StringUtils.isBlank(commercially)) {
      throw new BlankFieldException(COMMERCIALLY);
    }
    if (!commercially.equalsIgnoreCase(Boolean.TRUE.toString())
        && !commercially.equalsIgnoreCase(Boolean.FALSE.toString())) {
      throw new FieldParsingException(COMMERCIALLY);
    }
    this.commercially = Boolean.parseBoolean(commercially);
  }
}
