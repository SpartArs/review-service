package ru.spartars.review.error;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import ru.spartars.review.dto.ErrorResponseDto;
import ru.spartars.review.exception.RestApiException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Locale;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class DefaultErrorController extends AbstractErrorController {
  private final ErrorAttributes errorAttributes;
  private final MessageSource messageSource;

  public DefaultErrorController(ErrorAttributes errorAttributes, MessageSource messageSource) {
    super(errorAttributes);
    this.errorAttributes = errorAttributes;
    this.messageSource = messageSource;
  }

  @RequestMapping
  public ResponseEntity<ErrorResponseDto> error(HttpServletRequest request, Locale locale) {
    var webRequest = new ServletWebRequest(request);
    var throwable = errorAttributes.getError(webRequest);
    var status = getStatus(request);
    var defaultMessage = messageSource.getMessage("api.exception.default.message", null, locale);

    if (throwable == null) {
      return ResponseEntity.status(status).body(new ErrorResponseDto(defaultMessage, Collections.emptyList()));
    }

    if (throwable instanceof RestApiException) {
      return ResponseEntity.status(status).body(
          new ErrorResponseDto(
              messageSource.getMessage(throwable.getMessage(), null, defaultMessage, locale), Collections.emptyList()
          )
      );
    }

    // TODO: подумать: NPE ->
    return ResponseEntity.status(status).body(new ErrorResponseDto(defaultMessage, Collections.emptyList()));
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
